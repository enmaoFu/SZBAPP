package com.bjsz.app.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.bjsz.app.utils.BaseVerification;
import com.orhanobut.logger.Logger;

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

    private BaseNetworkJudge net = new BaseNetworkJudge(LoginActivity.this);

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
        final Intent intent = new Intent();
        String username;
        final String code;
        boolean flag;
        switch (v.getId()){
            case R.id.login_code_but:
                showDialog();
                username = login_username_edx.getText().toString().trim();
                flag = BaseVerification.isMobileNO(username);
                if(!username.equals("") && username != null){
                    if(flag == true){
                        boolean flags = net.isNetworkConnected(this);
                        if(flags == true){
                            ApiService as = initRetrofit(URL);
                            Call<CodeReturnData> call = as.getCode(username);
                            call.enqueue(new Callback<CodeReturnData>() {
                                @Override
                                public void onResponse(Call<CodeReturnData> call, Response<CodeReturnData> response) {
                                    int status = response.body().getStatus();
                                    if(status == 0){
                                        hideDialog();
                                        timer.start();
                                        showToast("发送验证码成功，请查收");
                                        Logger.v(response.body().getData().getCode()+"验证码...");
                                    }else{
                                        showToast("发送验证码失败，请重试");
                                    }
                                }

                                @Override
                                public void onFailure(Call<CodeReturnData> call, Throwable t) {
                                    hideDialog();
                                    Logger.v("发送验证码请求失败"+t.getMessage());
                                    showToast("服务器发生错误，请等待修复");
                                }
                            });
                        }else{
                            showToast("发送验证码失败，请检查网络");
                        }
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

                /*Retrofit retrofit = new Retrofit.Builder()
                        //使用自定义的mGsonConverterFactory
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(URL)
                        .build();
                ApiService as = retrofit.create(ApiService.class);
                Call<LoginData> news = as.getLogin(username,code);
                news.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        Logger.v("返回"+response.body().getMsg());
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Logger.v("登陆请求失败"+t.getMessage());
                        showToast("服务器发生错误，请等待修复");
                    }
                });*/

                boolean flags = net.checkNetworkAvailable();
                if(flags == true){
                    ApiService as = initRetrofit(URL);
                    Logger.v("输入的"+username+"  "+code);
                    Call<LoginData> call = as.getLogin(username,code);
                    call.enqueue(new Callback<LoginData>() {
                        @Override
                        public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                            Logger.v("返回"+response.body().getMsg());
                        }

                        @Override
                        public void onFailure(Call<LoginData> call, Throwable t) {
                            Logger.v("登陆请求失败"+t.getMessage());
                            showToast("服务器发生错误，请等待修复");
                        }
                    });
                }else{
                    showToast("登陆失败，请检查网络");
                }

                /*if(!username.equals("") && username != null){
                    if(flag == true){
                        if(!code.equals("") && code != null){

                            *//*if(username.equals("13452523216") && code.equals("1234")){
                                intent.setClass(this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                showToast("手机号或验证码错误");
                            }*//*
                        }else{
                            showToast("验证码不能为空");
                        }
                    }else{
                        showToast("请输入正确的手机号");
                    }
                }else{
                    showToast("手机号不能为空");
                }*/
                break;
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
