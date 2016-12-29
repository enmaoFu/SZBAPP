package com.bjsz.app.fragments.home;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.MyApplication;
import com.bjsz.app.R;
import com.bjsz.app.adapters.home.FragmentHomeAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.home.HomeGridviewOptionEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 首页
 * @author enmaoFu
 * @date 2016-12-26
 */
public class HomeFragment extends BaseFragment{

    private ImageView right_img;//标题栏右边消息
    private TextView center_text;//标题栏中间标题

    private GridView home_grid;//医生listview
    private List<HomeGridviewOptionEntity> homeGridviewOptionArrayList = new ArrayList<>();//数据集
    private FragmentHomeAdapter fragmentHomeAdapter;//适配器

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
        fragmentHomeAdapter = new FragmentHomeAdapter(getActivity());
        home_grid.setAdapter(fragmentHomeAdapter);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
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

}
