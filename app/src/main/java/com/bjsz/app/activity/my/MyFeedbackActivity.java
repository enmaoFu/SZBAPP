package com.bjsz.app.activity.my;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.returndata.my.FeedbackData;
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
 * 意见反馈页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class MyFeedbackActivity extends BaseActivity implements View.OnClickListener,BaseInterface{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private EditText my_fee_edx;//意见反馈输入框
    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_my_feedback);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        my_fee_edx = (EditText)findViewById(R.id.my_fee_edx);
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(MyFeedbackActivity.this);
        net = new BaseNetworkJudge(MyFeedbackActivity.this);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("意见反馈");
        right_text.setText("提交");
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
            case R.id.right_text:
                if(my_fee_edx.getText().toString().trim().equals("")){
                    showToast("请输入反馈内容");
                }else{
                    netWorkFeedback();
                }
                break;
        }
    }

    /**
     * 意见反馈
     */
    public void netWorkFeedback(){

        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            baseShowDialog();
            String numberPhoen = basePreference.getString("phoneNumber");
            String content = my_fee_edx.getText().toString().trim();
            ApiService as = initRetrofit(URL);
            Call<FeedbackData> call = as.netWorkFeedback(numberPhoen,content);
            call.enqueue(new Callback<FeedbackData>() {
                @Override
                public void onResponse(Call<FeedbackData> call, Response<FeedbackData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        baseHideDialog();
                        my_fee_edx.setText("");
                        showToast("提交意见反馈成功");
                    }else{
                        baseHideDialog();
                        showToast("提交意见反馈失败");
                    }
                }

                @Override
                public void onFailure(Call<FeedbackData> call, Throwable t) {
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
                    Logger.v("提交意见反馈失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，无法提交意见反馈");
        }

    }

}
