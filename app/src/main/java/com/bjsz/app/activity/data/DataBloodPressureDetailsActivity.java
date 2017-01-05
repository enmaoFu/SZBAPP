package com.bjsz.app.activity.data;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataBloodPressureDetailsAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataBloodPressureDetailsChildeEntity;
import com.bjsz.app.entity.data.DataBloodPressureDetailsGroupEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;

/**
 * 血压详情页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataBloodPressureDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边分析

    private ArrayList<DataBloodPressureDetailsGroupEntity> gData = null;
    private ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>> iData = null;
    private ArrayList<DataBloodPressureDetailsChildeEntity> lData = null;
    private ExpandableListView exlist_lol;
    private DataBloodPressureDetailsAdapter myAdapter = null;

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_blood_pressure_details);
    }

    /**
     * 初始化组件
     */
    @Override
    public void initView(){
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        exlist_lol = (ExpandableListView)findViewById(R.id.exlist_lol);
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initExpandableListView();
    }

    /**
     * 初始化标题栏
     */
    @Override
    public void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("检测报告");
        right_text.setText("分析");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
            case R.id.right_text:
                intent.setClass(this,DataHealthyAnalysisActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void initExpandableListView(){

        // 得到屏幕的大小
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //图标设置在右边
        exlist_lol.setIndicatorBounds(dm.widthPixels-60, dm.widthPixels);// 设置指示图标的位置

        //数据准备
        gData = new ArrayList<DataBloodPressureDetailsGroupEntity>();
        iData = new ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>>();
        gData.add(new DataBloodPressureDetailsGroupEntity("2014-02-12"));
        gData.add(new DataBloodPressureDetailsGroupEntity("2017-01-05"));
        gData.add(new DataBloodPressureDetailsGroupEntity("2016-09-01"));

        lData = new ArrayList<DataBloodPressureDetailsChildeEntity>();

        //AD组
        lData.add(new DataBloodPressureDetailsChildeEntity("16:30","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:30","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:30","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:30","112","60","68"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<DataBloodPressureDetailsChildeEntity>();
        lData.add(new DataBloodPressureDetailsChildeEntity("16:40","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:40","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:40","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:40","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:40","112","60","68"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<DataBloodPressureDetailsChildeEntity>();
        lData.add(new DataBloodPressureDetailsChildeEntity("16:50","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:50","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:50","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:50","112","60","68"));
        lData.add(new DataBloodPressureDetailsChildeEntity("16:50","112","60","68"));
        iData.add(lData);

        myAdapter = new DataBloodPressureDetailsAdapter(gData,iData,this);
        exlist_lol.setAdapter(myAdapter);

        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(DataBloodPressureDetailsActivity.this, "你点击了：" + iData.get(groupPosition).get(childPosition).getTextChild_one(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(DataBloodPressureDetailsActivity.this,DataPublicTestingPresentationDetailsActivity.class);
                startActivity(intent);
                return true;
            }
        });


    }

}
