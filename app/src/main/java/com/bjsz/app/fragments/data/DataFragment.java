package com.bjsz.app.fragments.data;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.activity.data.DataPublicDetailsActivity;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.entity.returndata.data.HealthyData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 健康数据
 * @author enmaoFu
 * @date 2016-12-26
 */
public class DataFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener,BaseInterface {

    private TextView center_text;//标题栏中间标题

    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private RelativeLayout data_heart_pulse_re;//心脉
    private RelativeLayout data_blood_pressure_re;//血压
    private RelativeLayout data_oxygen_re;//血氧
    private RelativeLayout data_blood_sugar_re;//血糖
    private RelativeLayout data_temperature_re;//体温
    private RelativeLayout data_uric_acid_re;//尿酸
    private RelativeLayout data_cholesterol_re;//胆固醇
    private RelativeLayout data_urine_routine_re;//尿常规
    private RelativeLayout data_ecg_re;//心电

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String uid;//用户ID

    private TextView xy_number;//血压
    private TextView xt_number;//血糖
    private TextView yx_number;//血氧
    private TextView tw_number;//体温
    private TextView ns_number;//尿酸
    private TextView dg_number;//胆固醇

    /**
     * 传递给健康数据通用检测报告详情页面
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

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_data;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);

        data_heart_pulse_re = (RelativeLayout)findViewById(R.id.data_heart_pulse_re);
        data_blood_pressure_re = (RelativeLayout)findViewById(R.id.data_blood_pressure_re);
        data_oxygen_re = (RelativeLayout)findViewById(R.id.data_oxygen_re);
        data_blood_sugar_re = (RelativeLayout)findViewById(R.id.data_blood_sugar_re);
        data_temperature_re = (RelativeLayout)findViewById(R.id.data_temperature_re);
        data_uric_acid_re = (RelativeLayout)findViewById(R.id.data_uric_acid_re);
        data_cholesterol_re = (RelativeLayout)findViewById(R.id.data_cholesterol_re);
        data_urine_routine_re = (RelativeLayout)findViewById(R.id.data_urine_routine_re);
        data_ecg_re = (RelativeLayout)findViewById(R.id.data_ecg_re);
        xy_number = (TextView)findViewById(R.id.xy_number);
        xt_number = (TextView)findViewById(R.id.xt_number);
        yx_number = (TextView)findViewById(R.id.yx_number);
        tw_number = (TextView)findViewById(R.id.tw_number);
        ns_number = (TextView)findViewById(R.id.ns_number);
        dg_number = (TextView)findViewById(R.id.dg_number);

        data_heart_pulse_re.setOnClickListener(this);
        data_blood_pressure_re.setOnClickListener(this);
        data_oxygen_re.setOnClickListener(this);
        data_blood_sugar_re.setOnClickListener(this);
        data_temperature_re.setOnClickListener(this);
        data_uric_acid_re.setOnClickListener(this);
        data_cholesterol_re.setOnClickListener(this);
        data_urine_routine_re.setOnClickListener(this);
        data_ecg_re.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(getActivity());
        net = new BaseNetworkJudge(getActivity());
        initSwipeRefreshLayout();
        netWorkGetHealthyData();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        center_text.setVisibility(View.VISIBLE);
        center_text.setText("健康数据");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);
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
        netWorkGetHealthyData();
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

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.data_heart_pulse_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_blood_pressure_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_BLOOD_PRESSURE);
                startActivity(intent);
                break;

            case R.id.data_oxygen_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_OXYGEN);
                startActivity(intent);
                break;

            case R.id.data_blood_sugar_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_BLOOD_SUGAR);
                startActivity(intent);
                break;

            case R.id.data_temperature_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_TEMPERATURE);
                startActivity(intent);
                break;

            case R.id.data_uric_acid_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_URIC_ACID);
                startActivity(intent);
                break;

            case R.id.data_cholesterol_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_CHOLESTEROL);
                startActivity(intent);
                break;

            case R.id.data_urine_routine_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_URINE_ROUTINE);
                startActivity(intent);
                break;

            case R.id.data_ecg_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataPublicDetailsActivity.class);
                intent.putExtra("key",VALUE_ECG);
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取健康数据
     */
    public void netWorkGetHealthyData(){

        final boolean flag = net.isNetworkConnected(getActivity());
        if(flag == true){
            swipeLayout.setRefreshing(true);
            uid = basePreference.getString("uid");//用户ID
            ApiService as = initRetrofit(URL);
            Call<HealthyData> call = as.getHealthyData(uid,"healthyData");
            call.enqueue(new Callback<HealthyData>() {
                @Override
                public void onResponse(Call<HealthyData> call, Response<HealthyData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){

                        if(response.body().getData().getXy().getSys().equals("")){
                            xy_number.setText(""+","+response.body().getData().getXy().getDia());
                        }else if(response.body().getData().getXy().getDia().equals("")){
                            xy_number.setText(response.body().getData().getXy().getSys()+","+"");
                        }else{
                            xy_number.setText(response.body().getData().getXy().getSys()+","+response.body().getData().getXy().getDia());
                        }

                        if(response.body().getData().getXt().getGlu().equals("")){
                            xt_number.setText("");
                        }else{
                            xt_number.setText(response.body().getData().getXt().getGlu());
                        }

                        if(response.body().getData().getYx().getSpo2().equals("")){
                            yx_number.setText(""+","+response.body().getData().getYx().getPr());
                        }else if(response.body().getData().getYx().getPr().equals("")){
                            yx_number.setText(response.body().getData().getYx().getSpo2()+","+"");
                        }else{
                            yx_number.setText(response.body().getData().getYx().getSpo2()+","+response.body().getData().getYx().getPr());
                        }

                        if(response.body().getData().getTw().getTemp().equals("")){
                            tw_number.setText("");
                        }else{
                            tw_number.setText(response.body().getData().getTw().getTemp());
                        }

                        if(response.body().getData().getNs().getUa().equals("")){
                            ns_number.setText("");
                        }else{
                            ns_number.setText(response.body().getData().getNs().getUa());
                        }

                        if(response.body().getData().getDg().getChol().equals("")){
                            dg_number.setText("");
                        }else{
                            dg_number.setText(response.body().getData().getDg().getChol());
                        }
                        swipeLayout.setRefreshing(false);

                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取健康数据失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<HealthyData> call, Throwable t) {
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
                    Logger.v("获取健康数据失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取健康数据失败");
        }

    }

}
