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

/**
 * 添加个人病史里 家族史页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesFamilyhistoryAddPastHistoryActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private RelativeLayout addjr;//填写家人名称弹框布局
    private RelativeLayout addjb;//填写疾病名称弹框布局

    private TextView msgjr;//家人名称
    private TextView msgjb;//疾病名称

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_familyhistory_add_past_history);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        addjr = (RelativeLayout)findViewById(R.id.addjr);
        addjb = (RelativeLayout)findViewById(R.id.addjb);
        msgjr = (TextView)findViewById(R.id.msgjr);
        msgjb = (TextView)findViewById(R.id.msgjb);
        addjr.setOnClickListener(this);
        addjb.setOnClickListener(this);
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
        center_text.setText("添加家族史");
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
            case R.id.addjr:
                showDialogJr();
                break;
            case R.id.addjb:
                showDialogJb();
                break;
        }
    }

    /**
     * 弹出对话框
     */
    public void showDialogJr(){
        final ActivityArchivesAddDialogView dialog = new ActivityArchivesAddDialogView(this,"添加家人名称","请填写家人名称");
        final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgjr.setText(editText.getText().toString().toString());
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

    /**
     * 弹出对话框
     */
    public void showDialogJb(){
        final ActivityArchivesAddDialogView dialog = new ActivityArchivesAddDialogView(this,"添加疾病名称","请填写疾病名称");
        final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgjb.setText(editText.getText().toString().toString());
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
