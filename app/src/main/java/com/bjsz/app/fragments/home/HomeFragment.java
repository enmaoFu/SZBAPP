package com.bjsz.app.fragments.home;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.home.FragmentHomeAdapter;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.home.HomeGridviewOptionEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
    private Map<Integer,String> homeGridviewImageMap = new HashMap<>();//gridview图片数据源

    @Override
    protected int bindViews() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        right_img = (ImageView)findViewById(R.id.right_img);
        center_text = (TextView)findViewById(R.id.center_text);
        home_grid = (GridView)findViewById(R.id.home_grid);
        fragmentHomeAdapter = new FragmentHomeAdapter(getActivity());
        home_grid.setAdapter(fragmentHomeAdapter);
    }

    @Override
    protected void initData() {
        homeGridviewImageMap.put(R.mipmap.ic_home_heart_pulse_img,"心脉");
        homeGridviewImageMap.put(R.mipmap.ic_home_blood_pressure_img,"血压");
        homeGridviewImageMap.put(R.mipmap.ic_home_oxygen_img,"血氧");
        homeGridviewImageMap.put(R.mipmap.ic_home_blood_sugar_img,"血糖");
        homeGridviewImageMap.put(R.mipmap.ic_home_temperature_img,"体温");
        homeGridviewImageMap.put(R.mipmap.ic_home_uric_acid_img,"尿酸");
        homeGridviewImageMap.put(R.mipmap.ic_home_cholesterol_img,"胆固醇");
        homeGridviewImageMap.put(R.mipmap.ic_home_urine_routine_img,"尿常规");
        homeGridviewImageMap.put(R.mipmap.ic_home_ecg_img,"心电");
        initGridview();
    }

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

        Iterator<Map.Entry<Integer, String>> it = homeGridviewImageMap.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, String> entry = it.next();
            homeGridviewOptionEntity = new HomeGridviewOptionEntity(entry.getKey(),entry.getValue());
            homeGridviewOptionArrayList.add(homeGridviewOptionEntity);

        }

        fragmentHomeAdapter.setItems(homeGridviewOptionArrayList);

    }

}
