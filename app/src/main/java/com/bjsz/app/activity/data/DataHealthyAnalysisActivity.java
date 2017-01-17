package com.bjsz.app.activity.data;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataHealthyAnalysisAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataHealthyAnalysisEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.views.BaseLyListView;
import com.bjsz.app.views.BaseRoundProgressBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康数据健康分析页面
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataHealthyAnalysisActivity extends BaseActivity implements View.OnClickListener {

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private BaseRoundProgressBarView data_bar_view;//自定义圆环
    private ScrollView data_healthy_analysis_scroll;//滚动
    private TextView data_healthy_analysis_more;

    private BaseLyListView data_healthy_analysis_list;//listview
    private List<DataHealthyAnalysisEntity> dataHealthyAnalysisEntityList = new ArrayList<>();//数据集
    private DataHealthyAnalysisAdapter dataHealthyAnalysisAdapter;//适配器

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_healthy_analysis);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView) findViewById(R.id.left_img);
        center_text = (TextView) findViewById(R.id.center_text);
        data_bar_view = (BaseRoundProgressBarView) findViewById(R.id.data_bar_view);
        data_healthy_analysis_list = (BaseLyListView)findViewById(R.id.data_healthy_analysis_list);
        data_healthy_analysis_scroll = (ScrollView)findViewById(R.id.data_healthy_analysis_scroll);
        data_healthy_analysis_more = (TextView)findViewById(R.id.data_healthy_analysis_more);
        dataHealthyAnalysisAdapter = new DataHealthyAnalysisAdapter(this);
        data_healthy_analysis_list.setAdapter(dataHealthyAnalysisAdapter);
        left_img.setOnClickListener(this);
        data_healthy_analysis_more.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        /**
         * 因布局中使用了自定义Listview重写了高度，ScrollView嵌套Listview导致冲突
         * 进入页面不在顶部0.0的坐标，所以加上smoothScrollTo(0,20);
         */
        data_healthy_analysis_scroll.smoothScrollTo(0,20);
        data_bar_view.setProgress(80);
        initList();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("健康分析");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.left_img:
                backView();
                break;
            case R.id.data_healthy_analysis_more:
                intent.setClass(this,DataHealthyAnalysisMoreActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 初始化时光轴列表
     */
    public void initList(){

        DataHealthyAnalysisEntity dataHealthyAnalysisEntity = null;

        for(int i = 0; i < 7; i++){

            dataHealthyAnalysisEntity = new DataHealthyAnalysisEntity("13:00","2018","03-03","高压108-低压90-正常");

            dataHealthyAnalysisEntityList.add(dataHealthyAnalysisEntity);

        }

        dataHealthyAnalysisAdapter.setItems(dataHealthyAnalysisEntityList);

    }

}