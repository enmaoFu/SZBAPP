package com.bjsz.app.fragments.home;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.MyApplication;
import com.bjsz.app.R;
import com.bjsz.app.activity.home.ArchivesMessageCoreActivity;
import com.bjsz.app.adapters.home.FragmentHomeAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.home.HomeGridviewOptionEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
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

    private BaseRiseNumberTextView home_total_number;

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
        fragmentHomeAdapter = new FragmentHomeAdapter(getActivity());
        home_grid.setAdapter(fragmentHomeAdapter);
        right_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initBaseRiseNumberTextView();
        initGridview();
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
            homeGridviewOptionEntity = new HomeGridviewOptionEntity(entry.getKey(),entry.getValue());
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

    public void initBaseRiseNumberTextView(){

        // 设置数据
        home_total_number.withNumber(300);
        // 设置动画播放时间
        home_total_number.setDuration(5000);
        // 开始播放动画
        home_total_number.start();
        // 监听动画播放结束
        home_total_number.setOnEndListener(new BaseRiseNumberTextView.EndListener() {

            @Override
            public void onEndFinish() {
                showToast("数字增长完毕");
            }
        });
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

}
