package com.bjsz.app.activity.data;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataHealthyAnalysisAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataHealthyAnalysisEntity;
import com.bjsz.app.entity.returndata.data.SevenTimesMoreData;
import com.bjsz.app.entity.returndata.data.SevenTimesMoreExpandData;
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
 * 健康数据健康分析页面 更多
 * @author enmaoFu
 * @date 2017-01-06
 */
public class DataHealthyAnalysisMoreActivity extends BaseActivity implements View.OnClickListener,BaseInterface,SwipeRefreshLayout.OnRefreshListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private ListView data_healthy_analysis_more_list;//listview
    private List<DataHealthyAnalysisEntity> dataHealthyAnalysisEntityList = new ArrayList<>();//数据集
    private DataHealthyAnalysisAdapter dataHealthyAnalysisAdapter;//适配器

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String phoneNumber;//获取缓存的手机号

    private String key;//接收七日更多传过来的数据

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_healthy_analysis_more);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView) findViewById(R.id.left_img);
        center_text = (TextView) findViewById(R.id.center_text);
        data_healthy_analysis_more_list = (ListView)findViewById(R.id.data_healthy_analysis_more_list);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        dataHealthyAnalysisAdapter = new DataHealthyAnalysisAdapter(this);
        data_healthy_analysis_more_list.setAdapter(dataHealthyAnalysisAdapter);
        left_img.setOnClickListener(this);
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
        initSwipeRefreshLayout();
        netWorkGetSevenTimesMoreData();
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
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_img:
                backView();
                break;
        }
    }

    /**
     * 获取近七次更多
     */
    public void netWorkGetSevenTimesMoreData(){

        final boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            swipeLayout.setRefreshing(true);
            phoneNumber = basePreference.getString("phoneNumber");//用户ID
            ApiService as = initRetrofit(URL);
            Call<SevenTimesMoreData> call = as.getSevenTimesMoreData(key,phoneNumber);
            call.enqueue(new Callback<SevenTimesMoreData>() {
                @Override
                public void onResponse(Call<SevenTimesMoreData> call, Response<SevenTimesMoreData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        List<SevenTimesMoreExpandData> stmedList = response.body().getData();
                        DataHealthyAnalysisEntity dataHealthyAnalysisEntity = null;
                        dataHealthyAnalysisEntityList.clear();
                        for(int i = 0; i < stmedList.size(); i++){
                            dataHealthyAnalysisEntity = new DataHealthyAnalysisEntity(stmedList.get(i).getTime(),stmedList.get(i).getYear(),
                                    stmedList.get(i).getMonth(),stmedList.get(i).getNums());
                            dataHealthyAnalysisEntityList.add(dataHealthyAnalysisEntity);
                        }
                        dataHealthyAnalysisAdapter.setItems(dataHealthyAnalysisEntityList);
                        swipeLayout.setRefreshing(false);
                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取数据失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<SevenTimesMoreData> call, Throwable t) {
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
                    Logger.v("获取数据失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取数据失败");
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
        netWorkGetSevenTimesMoreData();
    }
}
