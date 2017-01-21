package com.bjsz.app.activity.archives;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BasePreference;

/**
 * 个人信息页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class ArchivesPersonMessageActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private TextView archives_person_name;//姓名
    private TextView archives_person_sex;//性别
    private TextView archives_person_age;//年龄
    private TextView archives_person_phoneNumber;//手机号
    private TextView archives_person_number;//身份证号

    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_person_message);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        archives_person_name = (TextView)findViewById(R.id.archives_person_name);
        archives_person_sex = (TextView)findViewById(R.id.archives_person_sex);
        archives_person_age = (TextView)findViewById(R.id.archives_person_age);
        archives_person_phoneNumber = (TextView)findViewById(R.id.archives_person_phoneNumber);
        archives_person_number = (TextView)findViewById(R.id.archives_person_number);
        left_img .setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(ArchivesPersonMessageActivity.this);
        setPersonMessage();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("个人信息");
        right_text.setText("完成");
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
     * 设置个人信息,从SP存储里取出相应的信息并进行设置
     * 姓名，性别，年龄，手机号，身份证号
     */
    public void setPersonMessage(){

        String name = basePreference.getString("name");
        String sex = basePreference.getString("sex");
        String age = basePreference.getString("age");
        String phoneNumber = basePreference.getString("phoneNumber");
        String identityId = basePreference.getString("identityId");

        if(name.equals("")){
            archives_person_name.setText("请输入您的真实姓名");
        }else{
            archives_person_name.setText(name);
        }

        if(sex.equals("")){
            archives_person_sex.setText("请选择性别");
        }else{
            archives_person_sex.setText(sex);
        }

        if(age.equals("")){
            archives_person_age.setText("请输入年龄");
        }else{
            archives_person_age.setText(age);
        }

        if(phoneNumber.equals("")){
            archives_person_phoneNumber.setText("请输入您的手机号");
        }else{
            archives_person_phoneNumber.setText(phoneNumber);
        }

        if(identityId.equals("")){
            archives_person_number.setText("请输入身份证号");
        }else{
            archives_person_number.setText(identityId);
        }

    }

}
