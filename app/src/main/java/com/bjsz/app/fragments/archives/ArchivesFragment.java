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
import com.bjsz.app.activity.archives.ArchivesPublicQueryPastHistoryActivity;
import com.bjsz.app.adapters.archives.MyViewPagerAdapter;
import com.bjsz.app.adapters.archives.ViewpagerArchivewAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.archives.ViewpagerArchivewEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康档案
 * @author enmaoFu
 * @date 2016-12-26
 */
public class ArchivesFragment extends BaseFragment implements View.OnClickListener{

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

    private RelativeLayout apmh_jws;//既往史
    private RelativeLayout apmh_jzs;//家族史
    private RelativeLayout apmh_ycbs;//遗传病史

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
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
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
        apmh_ycbs = (RelativeLayout) archives_person_medical_history.findViewById(R.id.apmh_ycbs);

        initEssentialInformation();
        initHabitsAndCustoms();
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

        apmh_ycbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), ArchivesPublicQueryPastHistoryActivity.class);
                intent.putExtra("key","ycbs");
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
            } else if(number == 2){
                textView1.setTextColor(Color.parseColor("#323232"));
                textView2.setTextColor(Color.parseColor("#323232"));
                textView3.setTextColor(Color.parseColor("#51BEFF"));
            }
        }
    }

    /**
     * 初始化健康档案基本信息
     */
    public void initEssentialInformation(){

        viewpagerArchivewEntityArrayList.clear();

        ViewpagerArchivewEntity viewpagerArchivewEntity = null;

        for(int i = 0; i < 3; i++){

            viewpagerArchivewEntity = new ViewpagerArchivewEntity("身高","170cm",0);

            viewpagerArchivewEntityArrayList.add(viewpagerArchivewEntity);

        }

        viewpagerArchivewAdapter.setItems(viewpagerArchivewEntityArrayList);

    }

    /**
     * 初始化健康档案生活习惯
     */
    public void initHabitsAndCustoms(){

        viewpagerArchivewEntityArrayListha.clear();

        ViewpagerArchivewEntity viewpagerArchivewEntity = null;

        for(int i = 0; i < 6; i++){

            viewpagerArchivewEntity = new ViewpagerArchivewEntity("饮食是否规律","是",0);

            viewpagerArchivewEntityArrayListha.add(viewpagerArchivewEntity);

        }

        viewpagerArchivewAdapterha.setItems(viewpagerArchivewEntityArrayListha);

    }

}
