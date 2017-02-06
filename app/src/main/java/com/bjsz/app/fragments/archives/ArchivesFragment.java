package com.bjsz.app.fragments.archives;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.activity.archives.ArchivesPersonMessageActivity;
import com.bjsz.app.activity.archives.ArchivesPublicQueryPastHistoryActivity;
import com.bjsz.app.adapters.archives.MyViewPagerAdapter;
import com.bjsz.app.adapters.archives.ViewpagerArchivewAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.archives.ViewpagerArchivewEntity;
import com.bjsz.app.entity.returndata.archives.EssentialInformationData;
import com.bjsz.app.entity.returndata.archives.LifeHabitData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 健康档案
 * @author enmaoFu
 * @date 2016-12-26
 */
public class ArchivesFragment extends BaseFragment implements View.OnClickListener,BaseInterface{

    private TextView center_text;//标题栏中间标题

    private ViewPager viewPager;//页卡内容
    private TextView textView1,textView2,textView3;
    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private View archives_essential_information,archives_habits_and_customs,archives_person_medical_history;//各个页卡
    /*private int bmpW;// 动画图片宽度
    private ImageView imageView;// 动画图片*/

    private ListView archives_list;//listview
    private List<ViewpagerArchivewEntity> viewpagerArchivewEntityArrayList = new ArrayList<>();//数据集
    private ViewpagerArchivewAdapter viewpagerArchivewAdapter;//适配器

    private ListView archives_listha;//listview
    private List<ViewpagerArchivewEntity> viewpagerArchivewEntityArrayListha = new ArrayList<>();//数据集
    private ViewpagerArchivewAdapter viewpagerArchivewAdapterha;//适配器

    private RelativeLayout archives_person_msg_re;//个人信息
    private RelativeLayout apmh_jws;//既往史
    private RelativeLayout apmh_jzs;//家族史
    //private RelativeLayout apmh_ycbs;//遗传病史

    private TextView one_name;//姓名
    private TextView one_sex_age;//性别，年龄
    private BasePreference basePreference;//本地存储
    private BaseNetworkJudge net;//网络判断
    /*
     获取生活习惯开关，判断是否从网络获取，默认第一次从网络获取
     */
    private boolean getHabitsAndCustomsFlag;
    private String uid;//本地获取的uid

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_archives;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        archives_person_msg_re = (RelativeLayout)findViewById(R.id.archives_person_msg_re);
        one_name = (TextView)findViewById(R.id.one_name);
        one_sex_age = (TextView)findViewById(R.id.one_sex_age);
        archives_person_msg_re.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        /**
         * 第一次进入这个页面和生活习惯viewpager的时候从网络获取
         */
        getHabitsAndCustomsFlag = true;
        basePreference = new BasePreference(getActivity());
        net = new BaseNetworkJudge(getActivity());
        InitTextView();
        InitViewPager();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        center_text.setVisibility(View.VISIBLE);
        center_text.setText("健康档案");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.archives_person_msg_re:
                intent.setClass(getActivity(), ArchivesPersonMessageActivity.class);
                startActivity(intent);
                break;
        }

    }

    /**
     * 初始化viewpager
     */
    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        views = new ArrayList<View>();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        archives_essential_information = inflater.inflate(R.layout.viewpager_archives_essential_information,null);
        archives_habits_and_customs = inflater.inflate(R.layout.viewpager_archives_habits_and_customs, null);
        archives_person_medical_history = inflater.inflate(R.layout.viewpager_archives_person_medical_history,null);
        views.add(archives_essential_information);
        views.add(archives_habits_and_customs);
        views.add(archives_person_medical_history);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        archives_list = (ListView)archives_essential_information.findViewById(R.id.archives_list);
        archives_list.setDivider(new ColorDrawable(Color.parseColor("#F4F8F9")));
        archives_list.setDividerHeight(2);
        viewpagerArchivewAdapter = new ViewpagerArchivewAdapter(getActivity());
        archives_list.setAdapter(viewpagerArchivewAdapter);

        archives_listha = (ListView)archives_habits_and_customs.findViewById(R.id.archives_list);
        archives_listha.setDivider(new ColorDrawable(Color.parseColor("#F4F8F9")));
        archives_listha.setDividerHeight(2);
        viewpagerArchivewAdapterha = new ViewpagerArchivewAdapter(getActivity());
        archives_listha.setAdapter(viewpagerArchivewAdapterha);

        apmh_jws = (RelativeLayout) archives_person_medical_history.findViewById(R.id.apmh_jws);
        apmh_jzs = (RelativeLayout) archives_person_medical_history.findViewById(R.id.apmh_jzs);
        //apmh_ycbs = (RelativeLayout) archives_person_medical_history.findViewById(R.id.apmh_ycbs);

        NetworkGetCodeGetEssentialInformation();
        apmhOnclick();

    }

    /**
     * 既往史、家族史、遗传病史的监听
     */
    public void apmhOnclick(){

        final Intent intent = new Intent();

        apmh_jws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), ArchivesPublicQueryPastHistoryActivity.class);
                intent.putExtra("key","jws");
                startActivity(intent);
            }
        });

        apmh_jzs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), ArchivesPublicQueryPastHistoryActivity.class);
                intent.putExtra("key","jzs");
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     */
   /* private void InitImageView() {
        imageView= (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.s).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }*/

    /**
     * 初始化头标
     */
    private void InitTextView() {
        textView1 = (TextView) findViewById(R.id.archives_essential_information);
        textView2 = (TextView) findViewById(R.id.archives_habits_and_customs);
        textView3 = (TextView) findViewById(R.id.archives_person_medical_history);

        textView1.setTextColor(Color.parseColor("#51BEFF"));
        textView2.setTextColor(Color.parseColor("#323232"));
        textView3.setTextColor(Color.parseColor("#323232"));

        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }

    /**
     * 滑动的监听和页面变化
     * @author Administrator
     *
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /*int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量*/
        public void onPageScrollStateChanged(int arg0) {}

        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        public void onPageSelected(int arg0) {
            /*Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);*/
            //Toast.makeText(QroblemQueryActivity.this, "你选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
            int number = viewPager.getCurrentItem();
            if (number == 0) {
                textView1.setTextColor(Color.parseColor("#51BEFF"));
                textView2.setTextColor(Color.parseColor("#323232"));
                textView3.setTextColor(Color.parseColor("#323232"));
            } else if (number == 1) {
                textView1.setTextColor(Color.parseColor("#323232"));
                textView2.setTextColor(Color.parseColor("#51BEFF"));
                textView3.setTextColor(Color.parseColor("#323232"));
                /**
                 * 从网络获取后，改为false
                 * 只要这个fragment不销毁，在这个页面的三个viewpager之间跳转
                 * 就不会重复从网络获取，省流量
                 */
                if(getHabitsAndCustomsFlag){
                    NetworkHabitsAndCustoms();
                    getHabitsAndCustomsFlag = false;
                }else{
                    return;
                }
            } else if(number == 2){
                textView1.setTextColor(Color.parseColor("#323232"));
                textView2.setTextColor(Color.parseColor("#323232"));
                textView3.setTextColor(Color.parseColor("#51BEFF"));
            }
        }
    }

    /**
     * 获取健康档案基本信息，并设置
     */
    public void NetworkGetCodeGetEssentialInformation(){
        String name = basePreference.getString("name");//姓名
        String age = basePreference.getString("age");//年龄
        String sex = basePreference.getString("sex");//性别
        one_name.setText(name);
        one_sex_age.setText(sex+" "+age);
        boolean flags = net.isNetworkConnected(getActivity());
        if(flags == true){
            uid = basePreference.getString("uid");//uid
            ApiService as = initRetrofit(URL);
            Call<EssentialInformationData> call = as.getPersonMessage(uid);
            call.enqueue(new Callback<EssentialInformationData>() {
                @Override
                public void onResponse(Call<EssentialInformationData> call, Response<EssentialInformationData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        String height = response.body().getData().getHeight();//身高
                        String weight = response.body().getData().getWeight();//体重

                        viewpagerArchivewEntityArrayList.clear();

                        ViewpagerArchivewEntity viewpagerArchivewEntityHeight = null;
                        viewpagerArchivewEntityHeight = new ViewpagerArchivewEntity("身高",height.substring(0,height.length()-3)+"cm",0);

                        ViewpagerArchivewEntity viewpagerArchivewEntityWeight = null;
                        viewpagerArchivewEntityWeight = new ViewpagerArchivewEntity("体重",weight.substring(0,weight.length()-3)+"kg",0);

                        viewpagerArchivewEntityArrayList.add(viewpagerArchivewEntityHeight);
                        viewpagerArchivewEntityArrayList.add(viewpagerArchivewEntityWeight);

                        viewpagerArchivewAdapter.setItems(viewpagerArchivewEntityArrayList);
                    }else{
                        showToast("获取身高体重失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<EssentialInformationData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        hideDialog();
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        hideDialog();
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        hideDialog();
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取个人信息失败"+t.getMessage());
                }
            });

        }else{
            showToast("获取身高体重失败，请检查网络并下拉刷新");
        }
    }

    /**
     * 获取健康档案生活习惯，并设置
     */
    public void NetworkHabitsAndCustoms(){
        boolean flags = net.isNetworkConnected(getActivity());
        if(flags == true){
            ApiService as = initRetrofit(URL);
            Call<LifeHabitData> call = as.getLifeHabit(uid);
            call.enqueue(new Callback<LifeHabitData>() {
                @Override
                public void onResponse(Call<LifeHabitData> call, Response<LifeHabitData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){

                        viewpagerArchivewEntityArrayListha.clear();

                        ViewpagerArchivewEntity viewpagerArchivewEntity = null;

                        for(int i = 0; i < response.body().getData().size(); i++){

                            viewpagerArchivewEntity = new ViewpagerArchivewEntity(response.body().getData().get(i).getTitle(),
                                    response.body().getData().get(i).getContent(),0);

                            viewpagerArchivewEntityArrayListha.add(viewpagerArchivewEntity);

                        }

                        viewpagerArchivewAdapterha.setItems(viewpagerArchivewEntityArrayListha);

                    }else{
                        showToast("获取生活习惯失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<LifeHabitData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        hideDialog();
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        hideDialog();
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        hideDialog();
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取生活习惯失败"+t.getMessage());
                }
            });
        }else{
            showToast("获取生活习惯失败，请检查网络并下拉刷新");
        }

    }

}
