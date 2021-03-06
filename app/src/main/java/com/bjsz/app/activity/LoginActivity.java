package com.bjsz.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.returndata.CodeReturnData;
import com.bjsz.app.entity.returndata.LoginData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseCountDownTimer;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.utils.BaseVerification;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登陆页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener,BaseInterface{

    private EditText login_username_edx;//手机号
    private EditText login_code_edx;//验证码
    private Button login_code_but;//发送验证码
    private Button login_but;//登陆

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        login_username_edx = (EditText)findViewById(R.id.login_username_edx);
        login_code_edx = (EditText)findViewById(R.id.login_code_edx);
        login_code_but = (Button)findViewById(R.id.login_code_but);
        login_but = (Button)findViewById(R.id.login_but);
        login_code_but.setOnClickListener(this);
        login_but.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(LoginActivity.this);
        net = new BaseNetworkJudge(LoginActivity.this);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        String username;
        final String code;
        boolean flag;
        switch (v.getId()){
            case R.id.login_code_but:
                username = login_username_edx.getText().toString().trim();
                flag = BaseVerification.isMobileNO(username);
                if(!username.equals("") && username != null){
                    if(flag == true){
                        netWorkGetCode(username);
                    }else{
                        showToast("请输入正确的手机号");
                    }
                }else{
                    showToast("手机号不能为空");
                }
                break;
            case R.id.login_but:
                username = login_username_edx.getText().toString().trim();
                code = login_code_edx.getText().toString().toString();
                flag = BaseVerification.isMobileNO(username);
                if(!username.equals("") && username != null){
                    if(flag == true){
                        if(!code.equals("") && code != null){
                            netWorkLogin(username,code);
                        }else{
                            showToast("验证码不能为空");
                        }
                    }else{
                        showToast("请输入正确的手机号");
                    }
                }else{
                    showToast("手机号不能为空");
                }
                break;
        }
    }

    /**
     * 发送验证码
     */
    public void netWorkGetCode(String parameter){
        boolean flags = net.isNetworkConnected(this);
        if(flags == true){
            baseShowDialog();
            ApiService as = initRetrofit(URL);
            Call<CodeReturnData> call = as.getCode(parameter);
            call.enqueue(new Callback<CodeReturnData>() {
                @Override
                public void onResponse(Call<CodeReturnData> call, Response<CodeReturnData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        baseHideDialog();
                        timer.start();
                        showToast("发送验证码成功，请查收");
                        Logger.v(response.body().getData().getCode()+"验证码...");
                    }else{
                        baseHideDialog();
                        showToast("发送验证码失败，请重试");
                    }
                    Logger.v("验证码"+response.body().getStatus());
                }

                @Override
                public void onFailure(Call<CodeReturnData> call, Throwable t) {
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
                    Logger.v("发送验证码请求失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，无法发送验证码");
        }
    }

    /**
     * 登陆
     */
    public void netWorkLogin(String parameterPhone,String parameterCode){
        boolean flags = net.checkNetworkAvailable();
        final Intent intent = new Intent();
        if(flags == true){
            baseShowDialog();
            ApiService as = initRetrofit(URL);
            Call<LoginData> call = as.getLogin(parameterPhone,parameterCode);
            call.enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(Call<LoginData> call, final Response<LoginData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        /**
                         * 获取个人基本信息，保存到本地
                         */
                        final String uid = response.body().getData().getPersonMessage().getUid();//uid
                        final String name = response.body().getData().getPersonMessage().getName();//姓名
                        final String sex = response.body().getData().getPersonMessage().getSex();//性别
                        final String phoneNumber = response.body().getData().getPhoneNumber();//手机号
                        final String identityId = response.body().getData().getPersonMessage().getIdentityid();//身份证号
                        final String healthyKey = response.body().getData().getHealthyKey();//获取数据key

                        final int cardnum_age;
                        //从身份证获取出生日期，计算年龄
                        if(identityId.equals("")){
                            cardnum_age = 000000000000000000;
                        }else{
                            String cardnum = identityId.substring(6,10);
                            int age = Integer.parseInt(cardnum);
                            //获取现在年份
                            Time time = new Time("GMT+8");
                            time.setToNow();
                            //计算年龄
                            cardnum_age = time.year-age;
                        }

                        JPushInterface.setAlias(LoginActivity.this, //上下文对象
                                phoneNumber, //别名
                                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                                    @Override
                                    public void gotResult(int i, String s, Set<String> set) {
                                        if(i == 0){
                                            basePreference.setString("uid",uid);
                                            basePreference.setString("name",name);
                                            basePreference.setString("sex",sex);
                                            basePreference.setString("age",cardnum_age+"");//因为年龄计算出来是Int类型，此处加一个""变成字符串
                                            basePreference.setString("phoneNumber",phoneNumber);
                                            basePreference.setString("identityId",identityId);
                                            basePreference.setString("healthyKey",healthyKey);
                                            /**
                                             * 获取首页三个检测数据，保存到本地
                                             */
                                            String total = response.body().getData().getMeasureNumber().getTotal();//总测量条数
                                            String today = response.body().getData().getMeasureNumber().getToday();//今日测量条数
                                            String abnormal = response.body().getData().getMeasureNumber().getAbnormal();//异常测量条数
                                            basePreference.setString("total",total);
                                            basePreference.setString("today",today);
                                            basePreference.setString("abnormal",abnormal);
                                            baseHideDialog();
                                            Logger.v(i+"");
                                            intent.setClass(LoginActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            baseHideDialog();
                                            showToast("未知错误，请重新登录");
                                        }
                                    }
                                });

                    }else{
                        baseHideDialog();
                        showToast("验证码错误，登陆失败");
                    }
                    Logger.v("登陆"+response.body().getStatus());
                }

                @Override
                public void onFailure(Call<LoginData> call, Throwable t) {
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
                    Logger.v("登陆请求失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，无法登陆");
        }
    }

    /**
     * 发送验证码倒计时
     */
    BaseCountDownTimer timer = new BaseCountDownTimer(60000,1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            login_code_but.setBackgroundResource(R.drawable.shape_activity_login_code_but_false);
            login_code_but.setTextColor(Color.parseColor("#999999"));
            login_code_but.setEnabled(false);
            login_code_but.setText(millisUntilFinished/1000 + "秒后可重发");
        }

        @Override
        public void onFinish() {
            login_code_but.setBackgroundResource(R.drawable.shape_activity_login_code_but);
            login_code_but.setTextColor(Color.parseColor("#50BDFE"));
            login_code_but.setEnabled(true);
            login_code_but.setText("发送验证码");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}