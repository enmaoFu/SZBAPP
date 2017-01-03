package com.bjsz.app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseVerification;

/**
 * 登陆页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText login_username_edx;//手机号
    private EditText login_code_edx;//验证码
    private Button login_code_but;//发送验证码
    private Button login_but;//登陆

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
        Intent intent = new Intent();
        String username;
        String code;
        boolean flag;
        switch (v.getId()){
            case R.id.login_code_but:
                username = login_username_edx.getText().toString().trim();
                flag = BaseVerification.isMobileNO(username);
                if(!username.equals("") && username != null){
                    if(flag == true){
                        showToast("验证码已发送");
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
                            if(username.equals("13452523216") && code.equals("1234")){
                                intent.setClass(this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                showToast("手机号或验证码错误");
                            }
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
}
