package com.bjsz.app.activity.archives;

import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.returndata.archives.AddMedicalhistoryData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.views.ActivityArchivesAddDialogView;
import com.orhanobut.logger.Logger;
import com.suke.widget.SwitchButton;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import cn.qqtheme.framework.picker.DatePicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 添加个人病史里 既往史页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivestMedicalhistoryAddPastHistoryActivity extends BaseActivity implements View.OnClickListener,BaseInterface{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边完成

    private RelativeLayout dialog;//填写疾病名称弹框布局
    private TextView msg;//疾病名称
    private TextView archives_strat_date_text;//开始时间
    private TextView archives_stop_date_text;//结束时间
    private SwitchButton switch_button;//是否治愈
    private String isCheck = "0";//是否治愈标识，默认未治愈
    private TextView isCheckText;//是否治愈text显示
    private RelativeLayout if_cure;//治愈时间布局
    private View if_hr;//治愈时间分割线

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String uid;//本地获取的uid

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
        switch_button = (SwitchButton)findViewById(R.id.switch_button);
        if_cure = (RelativeLayout)findViewById(R.id.if_cure);
        if_hr = (View)findViewById(R.id.if_hr);
        isCheckText = (TextView)findViewById(R.id.isCheckText);
        archives_strat_date_text.setOnClickListener(this);
        archives_stop_date_text.setOnClickListener(this);
        dialog.setOnClickListener(this);
        left_img.setOnClickListener(this);
        right_text.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        net = new BaseNetworkJudge(this);
        basePreference = new BasePreference(this);
        switch_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked == true){
                    isCheck = "1";
                    isCheckText.setText("已治愈");
                    if_cure.setVisibility(View.VISIBLE);
                    if_hr.setVisibility(View.VISIBLE);
                }else{
                    isCheck = "0";
                    isCheckText.setText("未治愈");
                    if_cure.setVisibility(View.GONE);
                    if_hr.setVisibility(View.GONE);
                }
            }
        });
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
                DatePicker pickerStart = new DatePicker(this);
                pickerStart.setRange(1990, 2017);//年份范围
                pickerStart.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        archives_strat_date_text.setText(year + "-" + month + "-" + day);
                    }
                });
                pickerStart.show();
                break;
            case R.id.archives_stop_date_text:
                DatePicker pickerEnd = new DatePicker(this);
                pickerEnd.setRange(1990, 2017);//年份范围
                pickerEnd.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        archives_stop_date_text.setText(year + "-" + month + "-" + day);
                    }
                });
                pickerEnd.show();
                break;
            case R.id.dialog:
                showDialog();
                break;
            case R.id.right_text:
                AddMedicalhistory();
                /*Logger.v("用户ID"+uid+"--"+"疾病名称"+msg.getText().toString().trim()+"--"+"是否治愈"+isCheck+"--"+
                        "患病时间"+archives_strat_date_text.getText().toString().toString()+"--"+"治愈时间"+archives_stop_date_text.getText().toString().toString());*/
                break;
        }
    }

    /**
     * 添加既往史
     */
    public void AddMedicalhistory(){

        String diseaseName = msg.getText().toString().trim();//疾病名称
        String archivesStartDate = archives_strat_date_text.getText().toString().trim();//患病时间
        String archivewStopDate = archives_stop_date_text.getText().toString().trim();//治愈时间

        if(isCheck.equals("0")){
            if(diseaseName.equals("")){
                showToast("疾病名称不能为空");
            }else if(archivesStartDate.equals("患病时间")){
                showToast("请选择患病时间");
            }else{
                Logger.v("未治愈的时候");
                NetworkAddMedicalhistory(diseaseName,archivesStartDate,"");
            }
        }else if(isCheck.equals("1")){
            if(diseaseName.equals("")){
                showToast("疾病名称不能为空");
            }else if(archivesStartDate.equals("患病时间")){
                showToast("请选择患病时间");
            }else if(archivewStopDate.equals("治愈时间")){
                showToast("请选择治愈时间");
            }else{
                Logger.v("治愈的时候");
                NetworkAddMedicalhistory(diseaseName,archivesStartDate,archivewStopDate);
            }
        }

    }

    /**
     * 网络添加既往史
     * @param diseaseName
     * @param archivesStartDate
     * @param archivewStopDate
     */
    public void NetworkAddMedicalhistory(String diseaseName,String archivesStartDate,String archivewStopDate){
        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            baseShowDialog();
            uid = basePreference.getString("uid");//用户ID

            ApiService as = initRetrofit(URL);
            Call<AddMedicalhistoryData> call = as.getAddMedicalhistoryMesssage(uid,diseaseName,isCheck,archivesStartDate,archivewStopDate);
            call.enqueue(new Callback<AddMedicalhistoryData>() {
                @Override
                public void onResponse(Call<AddMedicalhistoryData> call, Response<AddMedicalhistoryData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        String illness = response.body().getData().getIllness();
                        String cure = response.body().getData().getCure();
                        //数据是使用Intent返回
                        Intent intent = new Intent();
                        //把返回数据存入Intent
                        intent.putExtra("illness", illness);
                        if(cure.equals("0")){
                            intent.putExtra("cure", "未治愈");
                        }else if(cure.equals("1")){
                            intent.putExtra("cure", "已治愈");
                        }
                        ArchivestMedicalhistoryAddPastHistoryActivity.this.setResult(1, intent);
                        baseHideDialog();
                        //关闭Activity
                        ArchivestMedicalhistoryAddPastHistoryActivity.this.finish();
                    }else{
                        baseHideDialog();
                        showToast("添加既往史失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<AddMedicalhistoryData> call, Throwable t) {
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
                    Logger.v("添加既往史信息失败"+t.getMessage());
                }
            });

        }else{
            showToast("添加既往史失败，请检查网络");
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
