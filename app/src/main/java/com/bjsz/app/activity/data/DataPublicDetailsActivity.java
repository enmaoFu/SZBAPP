package com.bjsz.app.activity.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataBloodPressureDetailsAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataBloodPressureDetailsChildeEntity;
import com.bjsz.app.entity.data.DataBloodPressureDetailsGroupEntity;
import com.bjsz.app.entity.returndata.data.CategoryData;
import com.bjsz.app.entity.returndata.data.CategoryExpandDateData;
import com.bjsz.app.entity.returndata.data.CategoryExpandDateTimeData;
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
 * 健康分析检测报告通用页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicDetailsActivity extends BaseActivity implements View.OnClickListener,BaseInterface,SwipeRefreshLayout.OnRefreshListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边分析
    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private TextView value_title;//值的标题
    private TextView data_blood_pressure_number;//检测总数

    private ArrayList<DataBloodPressureDetailsGroupEntity> gData = null;
    private ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>> iData = null;
    private ArrayList<DataBloodPressureDetailsChildeEntity> lData = null;
    private ExpandableListView exlist_lol;
    private DataBloodPressureDetailsAdapter myAdapter = null;

    /**
     * 健康数据页面传递过来的标识
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

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String phoneNumber;//获取缓存的手机号

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
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        exlist_lol = (ExpandableListView)findViewById(R.id.exlist_lol);
        value_title = (TextView)findViewById(R.id.value_title);
        data_blood_pressure_number = (TextView)findViewById(R.id.data_blood_pressure_number);
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
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
        judge();
        initSwipeRefreshLayout();
        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Toast.makeText(DataPublicDetailsActivity.this, "你点击了：" + iData.get(groupPosition).get(childPosition).getKey(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(DataPublicDetailsActivity.this,DataPublicTestingPresentationDetailsActivity.class);
                intent.putExtra("key",key);
                intent.putExtra("exlistkey",iData.get(groupPosition).get(childPosition).getKey());
                startActivity(intent);
                return true;
            }
        });
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
                value_title.setText("血压检测值");
                netWorkGetCategoryData(VALUE_BLOOD_PRESSURE);
                //showToast("血压");
                break;
            case VALUE_BLOOD_SUGAR:
                value_title.setText("血糖检测值");
                netWorkGetCategoryData(VALUE_BLOOD_SUGAR);
                //showToast("血糖");
                break;
            case VALUE_OXYGEN:
                value_title.setText("血氧检测值");
                netWorkGetCategoryData(VALUE_OXYGEN);
                //showToast("血氧");
                break;
            case VALUE_TEMPERATURE:
                value_title.setText("体温检测值");
                netWorkGetCategoryData(VALUE_TEMPERATURE);
                //showToast("体温");
                break;
            case VALUE_URIC_ACID:
                value_title.setText("尿酸检测值");
                netWorkGetCategoryData(VALUE_URIC_ACID);
                //showToast("尿酸");
                break;
            case VALUE_CHOLESTEROL:
                value_title.setText("胆固醇检测值");
                netWorkGetCategoryData(VALUE_CHOLESTEROL);
                //showToast("胆固醇");
                break;
            case VALUE_URINE_ROUTINE:
                value_title.setText("尿常规检测值");
                netWorkGetCategoryData(VALUE_URINE_ROUTINE);
                //showToast("尿常规");
                break;
            case VALUE_ECG:
                value_title.setText("心电检测值");
                netWorkGetCategoryData(VALUE_ECG);
                //showToast("心电");
                break;
        }

    }

    /**
     * 获取检测报告数据
     */
    public void netWorkGetCategoryData(String dataName){

        boolean flag = net.isNetworkConnected(this);
        if (flag == true){
            swipeLayout.setRefreshing(true);
            // 得到屏幕的大小
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            //图标设置在右边
            exlist_lol.setIndicatorBounds(dm.widthPixels-120, dm.widthPixels);// 设置指示图标的位置

            //数据准备
            gData = new ArrayList<DataBloodPressureDetailsGroupEntity>();
            iData = new ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>>();


            phoneNumber = basePreference.getString("phoneNumber");//用户ID
            ApiService as = initRetrofit(URL);
            Call<CategoryData> call = as.getCategoryData(phoneNumber,dataName);
            call.enqueue(new Callback<CategoryData>() {
                @Override
                public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){

                        String number = response.body().getData().getNumber();

                        if(number.equals("")){
                            data_blood_pressure_number.setText("");
                        }else{
                            data_blood_pressure_number.setText(number);
                        }

                        List<CategoryExpandDateData> list = response.body().getData().getDaynumber();
                        if(list.isEmpty()){
                            exlist_lol.setVisibility(View.GONE);
                            swipeLayout.setRefreshing(false);
                        }else{
                            for(int i = 0; i < list.size(); i++){
                                lData = new ArrayList<DataBloodPressureDetailsChildeEntity>();
                                //Logger.v("第一栏目"+list.get(i).getDay());
                                gData.add(new DataBloodPressureDetailsGroupEntity(list.get(i).getDay()));
                                List<CategoryExpandDateTimeData> cedtdList = response.body().getData().getDaynumber().get(i).getDatenumber();
                                for(int j = 0; j < cedtdList.size(); j++){
                                    //Logger.v("第二栏目"+cedtdList.get(j).getDate()+cedtdList.get(j).getValue());
                                    lData.add(new DataBloodPressureDetailsChildeEntity(cedtdList.get(j).getKey(),cedtdList.get(j).getDate(),cedtdList.get(j).getValue()));
                                }
                                iData.add(lData);
                            }
                            myAdapter = new DataBloodPressureDetailsAdapter(gData,iData);
                            exlist_lol.setAdapter(myAdapter);
                            swipeLayout.setRefreshing(false);
                        }
                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取检测报告失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<CategoryData> call, Throwable t) {
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
                    Logger.v("获取检测报告失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取检测报告失败");
        }

    }

    /**
     * 初始化设置下拉刷新
     */
    public void initSwipeRefreshLayout(){
        swipeLayout.setColorSchemeResources(R.color.colorSwipeLayout);
        swipeLayout.setOnRefreshListener(this);
    }

    public void onRefresh() {
        Logger.v("刷新...");
        switch (key){
            case VALUE_BLOOD_PRESSURE:
                netWorkGetCategoryData(VALUE_BLOOD_PRESSURE);
                //showToast("血压");
                break;
            case VALUE_BLOOD_SUGAR:
                netWorkGetCategoryData(VALUE_BLOOD_SUGAR);
                //showToast("血糖");
                break;
            case VALUE_OXYGEN:
                netWorkGetCategoryData(VALUE_OXYGEN);
                showToast("血氧");
                break;
            case VALUE_TEMPERATURE:
                netWorkGetCategoryData(VALUE_TEMPERATURE);
                //showToast("体温");
                break;
            case VALUE_URIC_ACID:
                netWorkGetCategoryData(VALUE_URIC_ACID);
                //showToast("尿酸");
                break;
            case VALUE_CHOLESTEROL:
                netWorkGetCategoryData(VALUE_CHOLESTEROL);
                //showToast("胆固醇");
                break;
            case VALUE_URINE_ROUTINE:
                netWorkGetCategoryData(VALUE_URINE_ROUTINE);
                //showToast("尿常规");
                break;
            case VALUE_ECG:
                netWorkGetCategoryData(VALUE_ECG);
                //showToast("心电");
                break;
        }
        /**
         * 模拟刷新 5秒后完成刷新(实际会是在网络请求中,届时将会取消此模拟)
         * 因为android是单线程，更新UI必须在UI线程中
         * 而Handler本身是在UI线程中的，所以直接在这里更新UI不会导致程序崩溃
         *//*
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task
                swipeLayout.setRefreshing(false);//完成刷新，关闭刷新
            }
        }, 5000);*/
    }
}
