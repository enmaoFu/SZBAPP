package com.bjsz.app.activity.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.activity.LoginActivity;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BasePreference;
import com.orhanobut.logger.Logger;
import com.suke.widget.SwitchButton;

/**
 * 设置页面
 * @author enmaoFu
 * @date 2017-01-01
 */
public class MySetingActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private SwitchButton switch_button;//开关
    private RelativeLayout my_about_as_re;//关于我们
    private RelativeLayout my_fee_re;//意见反馈
    private Button seting_logout;//退出

    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_my_seting);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        switch_button = (SwitchButton)findViewById(R.id.switch_button);
        my_about_as_re = (RelativeLayout)findViewById(R.id.my_about_as_re);
        my_fee_re = (RelativeLayout)findViewById(R.id.my_fee_re);
        seting_logout = (Button)findViewById(R.id.seting_logout);
        left_img.setOnClickListener(this);
        my_about_as_re.setOnClickListener(this);
        my_fee_re.setOnClickListener(this);
        seting_logout.setOnClickListener(this);
        initSwitchButton();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(MySetingActivity.this);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("设置");
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
            case R.id.my_about_as_re:
                intent.setClass(this,MyAboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_fee_re:
                intent.setClass(this,MyFeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.seting_logout:
                basePreference.removePreference();
                showToast("移除缓存数据成功...");
                break;
        }
    }

    /**
     * 初始化开关
     */
    public void initSwitchButton(){

        switch_button.setChecked(true);
        switch_button.isChecked();
        //开关状态
        //switch_button.toggle();
        //开关没有动画
        //switch_button.toggle(false);
        //禁用阴影效果
        //switch_button.setShadowEffect(true);
        //禁用按钮
        //switch_button.setEnabled(false);
        //禁用切换动画
        //switch_button.setEnableEffect(false);
        //监听开关状态
        switch_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked == true){
                    Logger.v("打开");
                }else {
                    Logger.v("关闭");
                }
            }
        });

    }

}
