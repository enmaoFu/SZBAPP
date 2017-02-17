package com.bjsz.app.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.returndata.home.NoticeMessageDetailsData;
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
 * 消息中心详情页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class ArchivesMessageDetailsActivity extends BaseActivity implements View.OnClickListener,BaseInterface{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView msg;//消息详情

    private String key;//接收消息通知传过来的某一条消息的标识

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_message_details);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        msg = (TextView)findViewById(R.id.msg);
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
        if(key.equals("home")){
            Bundle bundleHome = this.getIntent().getExtras();
            String getMes = bundleHome.getString("msg");
            msg.setText(getMes);
        }else{
            netWorkNoticeMessageDetails();
        }

    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("消息详情");
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
     * 获取通知详情
     */
    public void netWorkNoticeMessageDetails(){

        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            baseShowDialog();
            ApiService as = initRetrofit(URL);
            Call<NoticeMessageDetailsData> call = as.getNoticeMessageDetails(key);
            call.enqueue(new Callback<NoticeMessageDetailsData>() {
                @Override
                public void onResponse(Call<NoticeMessageDetailsData> call, Response<NoticeMessageDetailsData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        String str = response.body().getData();
                        msg.setText(str);
                        baseHideDialog();
                    }else{
                        baseHideDialog();
                        showToast("获取通知消息失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<NoticeMessageDetailsData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        baseHideDialog();
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        baseHideDialog();
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        baseHideDialog();
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取通知消息失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取通知消息失败");
        }

    }

}
