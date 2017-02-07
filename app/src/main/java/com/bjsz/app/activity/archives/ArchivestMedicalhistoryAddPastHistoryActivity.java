package com.bjsz.app.activity.archives;

import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.views.ActivityArchivesAddDialogView;

import cn.qqtheme.framework.picker.TimePicker;

/**
 * 公共的添加个人病史里 既往史，家族史，遗传病史页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivestMedicalhistoryAddPastHistoryActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private RelativeLayout dialog;//填写疾病名称弹框布局
    private TextView msg;//疾病名称
    private TextView archives_strat_date_text;//开始时间
    private TextView archives_stop_date_text;//结束时间

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_medicalhistory_add_past_history);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        archives_strat_date_text = (TextView)findViewById(R.id.archives_strat_date_text);
        archives_stop_date_text = (TextView)findViewById(R.id.archives_stop_date_text);
        dialog = (RelativeLayout)findViewById(R.id.dialog);
        msg = (TextView)findViewById(R.id.msg);
        archives_strat_date_text.setOnClickListener(this);
        archives_stop_date_text.setOnClickListener(this);
        dialog.setOnClickListener(this);
        left_img.setOnClickListener(this);
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
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        right_text.setText("完成");
        center_text.setText("添加既往史");
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
            case R.id.dialog:
                showDialog();
                break;
        }
    }

    /**
     * 弹出对话框
     */
    public void showDialog(){
        final ActivityArchivesAddDialogView dialog = new ActivityArchivesAddDialogView(this,"添加疾病名称","请填写疾病名称");
        final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText(editText.getText().toString().toString());
                dialog.cancel();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();//获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();//获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.4);//高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.85);//宽度设置为屏幕的0.5
        dialog.getWindow().setAttributes(p);//设置生效
        dialog.show();
    }

}
