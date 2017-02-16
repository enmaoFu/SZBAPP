package com.bjsz.app.activity.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataHealthyAnalysisAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataHealthyAnalysisEntity;
import com.bjsz.app.entity.returndata.data.HealthyAnalysisData;
import com.bjsz.app.entity.returndata.data.HealthyAnalysisExpandSevenData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.views.BaseLyListView;
import com.bjsz.app.views.BaseRoundProgressBarView;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 健康数据健康分析页面
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataHealthyAnalysisActivity extends BaseActivity implements View.OnClickListener,BaseInterface,SwipeRefreshLayout.OnRefreshListener {

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private BaseRoundProgressBarView data_bar_view;//自定义圆环
    private ScrollView data_healthy_analysis_scroll;//滚动
    private TextView data_healthy_analysis_more;
    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private BaseLyListView data_healthy_analysis_list;//listview
    private List<DataHealthyAnalysisEntity> dataHealthyAnalysisEntityList = new ArrayList<>();//数据集
    private DataHealthyAnalysisAdapter dataHealthyAnalysisAdapter;//适配器

    /**
     * 检测报告通用页面传递过来的标识
     * 2 表示血压
     * 3 表示血糖
     * 4 表示血氧
     * 5 表示体温
     * 6 表示尿酸
     * 7 表示胆固醇
     * 8 表示尿常规
     * 9 表示心电
     */
    private static final String VALUE_BLOOD_PRESSURE = "2";
    private static final String VALUE_BLOOD_SUGAR = "3";
    private static final String VALUE_OXYGEN = "4";
    private static final String VALUE_TEMPERATURE = "5";
    private static final String VALUE_URIC_ACID = "6";
    private static final String VALUE_CHOLESTEROL = "7";
    private static final String VALUE_URINE_ROUTINE = "8";
    private static final String VALUE_ECG = "9";
    private String key;//接收值

    private TextView normal;//正常的健康数据标题
    private TextView normal_number;//正常的健康数据值
    private TextView abnormal;//异常的健康数据标题
    private TextView abnormal_number;//异常的健康数据值
    private TextView data_healthy_analysis_day;//近七次
    private TextView measure;//测量详情

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String phoneNumber;//获取缓存的手机号

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
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        data_bar_view = (BaseRoundProgressBarView) findViewById(R.id.data_bar_view);
        data_healthy_analysis_list = (BaseLyListView)findViewById(R.id.data_healthy_analysis_list);
        data_healthy_analysis_scroll = (ScrollView)findViewById(R.id.data_healthy_analysis_scroll);
        data_healthy_analysis_more = (TextView)findViewById(R.id.data_healthy_analysis_more);
        normal = (TextView)findViewById(R.id.normal);
        normal_number = (TextView)findViewById(R.id.normal_number);
        abnormal = (TextView)findViewById(R.id.abnormal);
        abnormal_number = (TextView)findViewById(R.id.abnormal_number);
        measure = (TextView)findViewById(R.id.measure);
        data_healthy_analysis_day = (TextView)findViewById(R.id.data_healthy_analysis_day);
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
        basePreference = new BasePreference(this);
        net = new BaseNetworkJudge(this);
        Bundle bundle = this.getIntent().getExtras();
        key = bundle.getString("key");
        /**
         * 因布局中使用了自定义Listview重写了高度，ScrollView嵌套Listview导致冲突
         * 进入页面不在顶部0.0的坐标，所以加上smoothScrollTo(0,20);
         */
        data_healthy_analysis_scroll.smoothScrollTo(0,20);
        judge();
        initSwipeRefreshLayout();
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
                intent.putExtra("key",key);
                startActivity(intent);
                break;
        }
    }

    /**
     * 判断是页面的显示，根据传过来的值
     */
    public void judge(){

        switch (key){
            case VALUE_BLOOD_PRESSURE:
                normal.setText("正常血压");
                abnormal.setText("异常血压");
                data_healthy_analysis_day.setText("近七次血压");
                netWorkGetHealthyAnalysisData(VALUE_BLOOD_PRESSURE);
                //showToast("血压");
                break;
            case VALUE_BLOOD_SUGAR:
                normal.setText("正常血糖");
                abnormal.setText("异常血糖");
                data_healthy_analysis_day.setText("近七次血糖");
                netWorkGetHealthyAnalysisData(VALUE_BLOOD_SUGAR);
                //showToast("血糖");
                break;
            case VALUE_OXYGEN:
                normal.setText("正常血氧");
                abnormal.setText("异常血氧");
                data_healthy_analysis_day.setText("近七次血氧");
                netWorkGetHealthyAnalysisData(VALUE_OXYGEN);
                //showToast("血氧");
                break;
            case VALUE_TEMPERATURE:
                normal.setText("正常体温");
                abnormal.setText("异常体温");
                data_healthy_analysis_day.setText("近七次体温");
                netWorkGetHealthyAnalysisData(VALUE_TEMPERATURE);
                //showToast("体温");
                break;
            case VALUE_URIC_ACID:
                normal.setText("正常尿酸");
                abnormal.setText("异常尿酸");
                data_healthy_analysis_day.setText("近七次尿酸");
                netWorkGetHealthyAnalysisData(VALUE_URIC_ACID);
                //showToast("尿酸");
                break;
            case VALUE_CHOLESTEROL:
                normal.setText("正常胆固醇");
                abnormal.setText("异常胆固醇");
                data_healthy_analysis_day.setText("近七次胆固醇");
                netWorkGetHealthyAnalysisData(VALUE_CHOLESTEROL);
                //showToast("胆固醇");
                break;
            case VALUE_URINE_ROUTINE:
                normal.setText("正常尿常规");
                abnormal.setText("异常尿常规");
                data_healthy_analysis_day.setText("近七次尿常规");
                netWorkGetHealthyAnalysisData(VALUE_URINE_ROUTINE);
                //showToast("尿常规");
                break;
            case VALUE_ECG:
                normal.setText("正常心电");
                abnormal.setText("异常心电");
                data_healthy_analysis_day.setText("近七次心电");
                netWorkGetHealthyAnalysisData(VALUE_ECG);
                //showToast("心电");
                break;
        }

    }

    /**
     * 获取健康分析数据
     */
    public void netWorkGetHealthyAnalysisData(String dataName){

        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            swipeLayout.setRefreshing(true);
            phoneNumber = basePreference.getString("phoneNumber");//用户ID
            ApiService as = initRetrofit(URL);
            Call<HealthyAnalysisData> call = as.getHealthyAnalysisData(dataName,phoneNumber);
            call.enqueue(new Callback<HealthyAnalysisData>() {
                @Override
                public void onResponse(Call<HealthyAnalysisData> call, Response<HealthyAnalysisData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        normal_number.setText(response.body().getData().getZc());
                        abnormal_number.setText(response.body().getData().getYc());
                        data_bar_view.setProgress(response.body().getData().getNormal_rate());
                        List<HealthyAnalysisExpandSevenData> haesdList = response.body().getData().getDays();
                        DataHealthyAnalysisEntity dataHealthyAnalysisEntity = null;
                        dataHealthyAnalysisEntityList.clear();
                        for(int i = 0; i < haesdList.size(); i++){
                            dataHealthyAnalysisEntity = new DataHealthyAnalysisEntity(haesdList.get(i).getTime(),haesdList.get(i).getYear(),
                                    haesdList.get(i).getMonth(),haesdList.get(i).getNums());
                            dataHealthyAnalysisEntityList.add(dataHealthyAnalysisEntity);
                        }
                        dataHealthyAnalysisAdapter.setItems(dataHealthyAnalysisEntityList);
                        measure.setText(response.body().getData().getMeasure_details());
                        swipeLayout.setRefreshing(false);
                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取健康分析失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<HealthyAnalysisData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        swipeLayout.setRefreshing(false);
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        swipeLayout.setRefreshing(false);
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        swipeLayout.setRefreshing(false);
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取健康分析失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取健康分析失败");
        }

    }

    /**
     * 初始化设置下拉刷新
     */
    public void initSwipeRefreshLayout(){
        swipeLayout.setColorSchemeResources(R.color.colorSwipeLayout);
        swipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        switch (key){
            case VALUE_BLOOD_PRESSURE:
                netWorkGetHealthyAnalysisData(VALUE_BLOOD_PRESSURE);
                //showToast("血压");
                break;
            case VALUE_BLOOD_SUGAR:
                netWorkGetHealthyAnalysisData(VALUE_BLOOD_SUGAR);
                //showToast("血糖");
                break;
            case VALUE_OXYGEN:
                netWorkGetHealthyAnalysisData(VALUE_OXYGEN);
                //showToast("血氧");
                break;
            case VALUE_TEMPERATURE:
                netWorkGetHealthyAnalysisData(VALUE_TEMPERATURE);
                //showToast("体温");
                break;
            case VALUE_URIC_ACID:
                netWorkGetHealthyAnalysisData(VALUE_URIC_ACID);
                //showToast("尿酸");
                break;
            case VALUE_CHOLESTEROL:
                netWorkGetHealthyAnalysisData(VALUE_CHOLESTEROL);
                //showToast("胆固醇");
                break;
            case VALUE_URINE_ROUTINE:
                netWorkGetHealthyAnalysisData(VALUE_URINE_ROUTINE);
                //showToast("尿常规");
                break;
            case VALUE_ECG:
                netWorkGetHealthyAnalysisData(VALUE_ECG);
                //showToast("心电");
                break;
        }
    }
}
