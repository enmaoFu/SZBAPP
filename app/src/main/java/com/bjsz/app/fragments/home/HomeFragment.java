package com.bjsz.app.fragments.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.MyApplication;
import com.bjsz.app.R;
import com.bjsz.app.activity.data.DataPublicDetailsActivity;
import com.bjsz.app.activity.home.ArchivesMessageCoreActivity;
import com.bjsz.app.adapters.home.FragmentHomeAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.home.HomeGridviewOptionEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.views.BaseRiseNumberTextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 首页
 * @author enmaoFu
 * @date 2016-12-26
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private ImageView right_img;//标题栏右边消息
    private TextView center_text;//标题栏中间标题

    private GridView home_grid;//医生listview
    private List<HomeGridviewOptionEntity> homeGridviewOptionArrayList = new ArrayList<>();//数据集
    private FragmentHomeAdapter fragmentHomeAdapter;//适配器

    private BaseRiseNumberTextView home_total_number;//数字动画view
    private TextView home_total_number_left;//今日测量条数
    private TextView home_total_number_right;//异常测量条数
    private BasePreference basePreference;//本地存储

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_home;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        right_img = (ImageView)findViewById(R.id.right_img);
        center_text = (TextView)findViewById(R.id.center_text);
        home_grid = (GridView)findViewById(R.id.home_grid);
        home_total_number = (BaseRiseNumberTextView)findViewById(R.id.home_total_number);
        home_total_number_left = (TextView)findViewById(R.id.home_total_number_left);
        home_total_number_right = (TextView)findViewById(R.id.home_total_number_right);
        fragmentHomeAdapter = new FragmentHomeAdapter(getActivity());
        home_grid.setAdapter(fragmentHomeAdapter);
        right_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(getActivity());
        setNumber();
        initGridview();
        home_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = homeGridviewOptionArrayList.get(position).getKey().toString().trim();
                Intent intent = new Intent();
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        right_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_img.setImageResource(R.mipmap.ic_home_message);
        center_text.setText("时诊宝");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);

    }

    /**
     * 初始化Gridview选项
     */
    public void initGridview(){

        homeGridviewOptionArrayList.clear();

        HomeGridviewOptionEntity homeGridviewOptionEntity = null;

        //获得首页girdview数据源，使用迭代器进行循环读取
        Iterator<Map.Entry<Integer, String>> it = MyApplication.homeGridviewImageMap.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, String> entry = it.next();
            homeGridviewOptionEntity = new HomeGridviewOptionEntity(entry.getKey(),entry.getValue().substring(0,entry.getValue().length()-1),
                    entry.getValue().substring(entry.getValue().length()-1,entry.getValue().length()-0));
            homeGridviewOptionArrayList.add(homeGridviewOptionEntity);

        }

        fragmentHomeAdapter.setItems(homeGridviewOptionArrayList);

    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.right_img:
                intent.setClass(getActivity(), ArchivesMessageCoreActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void initBaseRiseNumberTextView(int number){

        // 设置数据
        home_total_number.withNumber(number);
        // 设置动画播放时间
        home_total_number.setDuration(5000);
        // 开始播放动画
        home_total_number.start();
        // 监听动画播放结束
        home_total_number.setOnEndListener(new BaseRiseNumberTextView.EndListener() {

            @Override
            public void onEndFinish() {
                //showToast("总数量增长完毕");
            }
        });
    }

    /**
     * 设置三条测量数据
     */
    public void setNumber(){
        String total = basePreference.getString("total");
        String today = basePreference.getString("today");
        String abnormal = basePreference.getString("abnormal");
        if(today.equals("")){
            initBaseRiseNumberTextView(0);
        }else{
            int number = Integer.parseInt(total);
            initBaseRiseNumberTextView(number);
        }

        if(today.equals("")){
            home_total_number_left.setText(0);
        }else{
            home_total_number_left.setText(today);
        }

        if(abnormal.equals("")){
            home_total_number_right.setText(0);
        }else{
            home_total_number_right.setText(abnormal);
        }

    }

    /**
     * 因为切换fragment的时候，会进行销毁调用oeDestroy方法，所以在这里要对动画进行取消
     * 否则因为切换后动画还在进行，当监听结束后对该页面进行UI操作是，因为该fragment已被销毁，所以会报空指针异常
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消动画
        home_total_number.cancel();
    }

    /**
     * 测试JSON
     * 三个实体类分别是
     *
     * public class A {

       private int status;

       private String msg;

       private List<B> data;

      }
     *
     *
     * public class B {

       private String a;

       private String b;

       private String c;

       private List<C> days;

       private String d;

      }

     *
     *
     * public class C {

       private String a;

       private String b;

       private String c;

        private String d;

      }
     *
     */
    /*public void json(){

        A a = new A();
        List<B> listb = new ArrayList<>();
        List<C> listc = new ArrayList<>();
        B b = new B();
        C c = new C();
        C c1 = new C();

        c.setA("a");
        c.setB("b");
        c.setC("c");
        c.setD("d");
        c1.setA("a");
        c1.setB("b");
        c1.setC("c");
        c1.setD("d");
        listc.add(c);
        listc.add(c1);

        b.setA("a");
        b.setB("bb");
        b.setC("c");
        b.setD("d");
        b.setDays(listc);
        listb.add(b);

        a.setStatus(0);
        a.setMsg("测试");
        a.setData(listb);

        Gson gson = new Gson();
        String result = gson.toJson(a);
        Logger.v(result);

    }*/

}
