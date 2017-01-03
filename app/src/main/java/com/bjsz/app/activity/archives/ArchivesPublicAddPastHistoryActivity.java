package com.bjsz.app.activity.archives;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import cn.qqtheme.framework.picker.TimePicker;

/**
 * 公共的添加个人病史里 既往史，家族史，遗传病史页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesPublicAddPastHistoryActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private String keyValue;//接收传过来的值，便于判断是点击的既往史，家族史，遗传病史的哪一个

    /**
     * 这四个组件用于添加的时候,根据既往史，家族史，遗传病史动态改变
     */
    private TextView arc_one_add_left;//添加项第一栏左边的名称
    private TextView arc_to_add_left;//添加项第二栏左边的名称
    private ImageView arc_to_add_left_img;//添加项第二栏右边的箭头图标
    private LinearLayout arc_to_add_left_lin;//添加项第二栏右边的选择时间
    private TextView archives_strat_date_text;//开始时间
    private TextView archives_stop_date_text;//结束时间

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_public_add_past_history);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        arc_one_add_left = (TextView)findViewById(R.id.arc_one_add_left);
        arc_to_add_left = (TextView)findViewById(R.id.arc_to_add_left);
        arc_to_add_left_lin = (LinearLayout)findViewById(R.id.arc_to_add_left_lin);
        arc_to_add_left_img = (ImageView)findViewById(R.id.arc_to_add_left_img);
        archives_strat_date_text = (TextView)findViewById(R.id.archives_strat_date_text);
        archives_stop_date_text = (TextView)findViewById(R.id.archives_stop_date_text);
        archives_strat_date_text.setOnClickListener(this);
        archives_stop_date_text.setOnClickListener(this);
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        keyValue = bundle.getString("key");
        initAddData();
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
        right_text.setText("完成");
        switch (keyValue){
            case "jws":
                center_text.setText("添加既往史");
                break;
            case "jzs":
                center_text.setText("添加家族史");
                break;
            case "ycbs":
                center_text.setText("添加遗传病史");
                break;
        }
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
            case R.id.archives_strat_date_text:
                TimePicker pickerStart = new TimePicker(this, TimePicker.HOUR_24);
                pickerStart.setRangeStart(0, 0);//00:00
                pickerStart.setRangeEnd(23, 00);//23:00
                pickerStart.setTopLineVisible(false);
                pickerStart.setOnTimePickListener(new TimePicker.OnTimePickListener() {
                    @Override
                    public void onTimePicked(String hour, String minute) {
                        //showToast(hour + ":" + minute);
                        archives_strat_date_text.setText(hour + ":" + minute);
                    }
                });
                pickerStart.show();
                break;
            case R.id.archives_stop_date_text:
                TimePicker pickerEnd = new TimePicker(this, TimePicker.HOUR_24);
                pickerEnd.setRangeStart(0, 0);//00:00
                pickerEnd.setRangeEnd(23, 00);//23:00
                pickerEnd.setTopLineVisible(false);
                pickerEnd.setOnTimePickListener(new TimePicker.OnTimePickListener() {
                    @Override
                    public void onTimePicked(String hour, String minute) {
                        //showToast(hour + ":" + minute);
                        archives_stop_date_text.setText(hour + ":" + minute);
                    }
                });
                pickerEnd.show();
                break;
        }
    }

    /**
     * 根据既往史，家族史，遗传病史初始化添加项
     */
    public void initAddData(){

        switch (keyValue){

            case "jws":
                arc_one_add_left.setText("疾病名称");
                arc_to_add_left.setText("日期");
                arc_to_add_left_lin.setVisibility(View.VISIBLE);
                break;
            case "jzs":
                arc_one_add_left.setText("家人姓名");
                arc_to_add_left.setText("疾病名称");
                arc_to_add_left_img.setVisibility(View.VISIBLE);
                break;
            case "ycbs":
                arc_one_add_left.setText("遗传病史");
                arc_to_add_left.setText("残疾情况");
                arc_to_add_left_img.setVisibility(View.VISIBLE);
                break;

        }


    }


}
