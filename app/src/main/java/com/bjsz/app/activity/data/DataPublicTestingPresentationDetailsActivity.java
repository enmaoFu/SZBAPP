package com.bjsz.app.activity.data;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataPublicTestingPresentationDetailsGridAdapter;
import com.bjsz.app.adapters.data.DataPublicTestingPresentationDetailsListAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsGridEntity;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsListEntity;
import com.bjsz.app.entity.returndata.data.CategoryDetailsData;
import com.bjsz.app.entity.returndata.data.CategoryDetailsExpandGridViewData;
import com.bjsz.app.entity.returndata.data.CategoryDetailsExpandListViewData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.views.BaseLyGridView;
import com.bjsz.app.views.BaseLyListView;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 健康数据通用检测报告详情页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsActivity extends BaseActivity implements View.OnClickListener,BaseInterface,SwipeRefreshLayout.OnRefreshListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private ImageView right_img;//标题栏右边更多
    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private TextView measure_time;//测量时间
    private TextView reminder;//温馨提示
    private TextView measure_details_msg;//测量详情信息

    private BaseLyGridView data_public_testing_gridview;//gridview
    private List<DataPublicTestingPresentationDetailsGridEntity> dataPublicTestingPresentationDetailsEntityArrayList = new ArrayList<>();//数据集
    private DataPublicTestingPresentationDetailsGridAdapter dataPublicTestingPresentationDetailsAdapter;//适配器

    private BaseLyListView data_public_testing_listview;//listview
    private List<DataPublicTestingPresentationDetailsListEntity> dataPublicTestingPresentationDetailsListEntityArrayList = new ArrayList<>();//数据集
    private DataPublicTestingPresentationDetailsListAdapter dataPublicTestingPresentationDetailsListAdapter;//适配器

    private RelativeLayout data_public_testing_top;//横向图片

    private String key;//接收传过来的值，便于判断是点击的血压到心电的哪一个
    private String exlistkey;//接收传过来的值，便于判断是点击的哪一条二级栏目
    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String phoneNumber;//用户手机号 本地存储获取赋值用

    /**
     * 传递给健康数据通用检测报告详情页面
     * 默认为1 表示通用的
     * 2 表示尿常规
     * 3 表示心电
     */
    private static final String VALUE_PUBLIC = "1";
    private static final String VALUE_URINE_ROUTINE = "2";
    private static final String VALUE_ECG = "3";

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_public_testing_presentation_details);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        measure_time = (TextView)findViewById(R.id.measure_time);
        reminder = (TextView)findViewById(R.id.reminder);
        measure_details_msg = (TextView)findViewById(R.id.measure_details_msg);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        data_public_testing_gridview = (BaseLyGridView)findViewById(R.id.data_public_testing_gridview);
        dataPublicTestingPresentationDetailsAdapter = new DataPublicTestingPresentationDetailsGridAdapter(this);
        data_public_testing_gridview.setAdapter(dataPublicTestingPresentationDetailsAdapter);
        data_public_testing_listview = (BaseLyListView)findViewById(R.id.data_public_testing_listview);
        dataPublicTestingPresentationDetailsListAdapter = new DataPublicTestingPresentationDetailsListAdapter(this);
        data_public_testing_listview.setAdapter(dataPublicTestingPresentationDetailsListAdapter);
        data_public_testing_top = (RelativeLayout)findViewById(R.id.data_public_testing_top);
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
        exlistkey = bundle.getString("exlistkey");
        //Logger.v("血压到心电的2-9:"+key+"第二栏目标识"+exlistkey);
        initSwipeRefreshLayout();
        netWorkGetTestingPresentationDetails();
        /*Bundle bundle = this.getIntent().getExtras();
        keyValue = bundle.getString("key");
        switch (keyValue){
            case VALUE_PUBLIC:
                initGridview();
                initListview();
                break;
            case VALUE_URINE_ROUTINE:
                initGridview();
                initListview();
                break;
            case VALUE_ECG:
                data_public_testing_top.setVisibility(View.VISIBLE);
                initGridview();
                initListview();
                break;
        }*/

    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("检测报告");
        right_img.setImageResource(R.mipmap.ic_right_more_img);
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
        }
    }

    /**
     * 获取检测报告详情
     */
    public void netWorkGetTestingPresentationDetails(){

        final boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            swipeLayout.setRefreshing(true);
            phoneNumber = basePreference.getString("phoneNumber");
            ApiService as = initRetrofit(URL);
            Call<CategoryDetailsData> call = as.getCategoryDetailsData(phoneNumber,key,exlistkey);
            call.enqueue(new Callback<CategoryDetailsData>() {
                @Override
                public void onResponse(Call<CategoryDetailsData> call, Response<CategoryDetailsData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){

                        String measureTime = response.body().getData().getTimes();
                        String reminderMsg = response.body().getData().getReminder();
                        String measureDetailsMsg = response.body().getData().getResult();

                        if(measureTime.equals("")){
                            measure_time.setText("暂无");
                        }else{
                            measure_time.setText("最新测量时间："+measureTime);
                        }

                        if(reminderMsg.equals("")){
                            reminder.setText("暂无");
                        }else{
                            reminder.setText(reminderMsg);
                        }

                        if(measureDetailsMsg.equals("")){
                            measure_details_msg.setText("暂无");
                        }else{
                            measure_details_msg.setText(measureDetailsMsg);
                        }

                        List<CategoryDetailsExpandGridViewData> cdegdList = response.body().getData().getGridview();
                        DataPublicTestingPresentationDetailsGridEntity dataPublicTestingPresentationDetailsEntity = null;
                        dataPublicTestingPresentationDetailsEntityArrayList.clear();
                        for(int i = 0; i < cdegdList.size(); i++){
                            dataPublicTestingPresentationDetailsEntity = new DataPublicTestingPresentationDetailsGridEntity("正常",
                                    cdegdList.get(i).getNumber(),cdegdList.get(i).getTitle());
                            dataPublicTestingPresentationDetailsEntityArrayList.add(dataPublicTestingPresentationDetailsEntity);
                        }
                        dataPublicTestingPresentationDetailsAdapter.setItems(dataPublicTestingPresentationDetailsEntityArrayList);


                        List<CategoryDetailsExpandListViewData> cdelvList = response.body().getData().getListview();
                        DataPublicTestingPresentationDetailsListEntity dataPublicTestingPresentationDetailsListEntity = null;
                        dataPublicTestingPresentationDetailsListEntityArrayList.clear();
                        for(int i = 0; i < cdelvList.size(); i++){
                            dataPublicTestingPresentationDetailsListEntity = new DataPublicTestingPresentationDetailsListEntity(cdelvList.get(i).getTitle(),
                                    cdelvList.get(i).getNumber(),cdelvList.get(i).getRange());
                            dataPublicTestingPresentationDetailsListEntityArrayList.add(dataPublicTestingPresentationDetailsListEntity);
                        }
                        dataPublicTestingPresentationDetailsListAdapter.setItems(dataPublicTestingPresentationDetailsListEntityArrayList);
                        swipeLayout.setRefreshing(false);

                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取检测报告失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<CategoryDetailsData> call, Throwable t) {
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
            showToast("获取检测报告失败，请重试");
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
        netWorkGetTestingPresentationDetails();
    }
}
