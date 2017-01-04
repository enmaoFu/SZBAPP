package com.bjsz.app.fragments.data;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.activity.data.DataBloodPressureDetailsActivity;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.orhanobut.logger.Logger;

/**
 * 健康数据
 * @author enmaoFu
 * @date 2016-12-26
 */
public class DataFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private TextView center_text;//标题栏中间标题

    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private RelativeLayout data_heart_pulse_re;//心脉
    private RelativeLayout data_blood_pressure_re;//血压
    private RelativeLayout data_oxygen_re;//血氧
    private RelativeLayout data_blood_sugar_re;//血糖
    private RelativeLayout data_temperature_re;//体温
    private RelativeLayout data_uric_acid_re;//尿酸
    private RelativeLayout data_cholesterol_re;//胆固醇
    private RelativeLayout data_urine_routine_re;//尿常规
    private RelativeLayout data_ecg_re;//心电

    /**
     * 传递给健康数据通用检测报告详情页面
     * 默认为1 表示通用的
     * 2 表示尿常规
     * 3 表示心电
     */
    private static final String VALUE_PUBLIC = "1";
    private static final String VALUE_URINE_ROUTINE = "2";
    private static final String VALUE_ECG = "3";

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_data;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);

        data_heart_pulse_re = (RelativeLayout)findViewById(R.id.data_heart_pulse_re);
        data_blood_pressure_re = (RelativeLayout)findViewById(R.id.data_blood_pressure_re);
        data_oxygen_re = (RelativeLayout)findViewById(R.id.data_oxygen_re);
        data_blood_sugar_re = (RelativeLayout)findViewById(R.id.data_blood_sugar_re);
        data_temperature_re = (RelativeLayout)findViewById(R.id.data_temperature_re);
        data_uric_acid_re = (RelativeLayout)findViewById(R.id.data_uric_acid_re);
        data_cholesterol_re = (RelativeLayout)findViewById(R.id.data_cholesterol_re);
        data_urine_routine_re = (RelativeLayout)findViewById(R.id.data_urine_routine_re);
        data_ecg_re = (RelativeLayout)findViewById(R.id.data_ecg_re);

        data_heart_pulse_re.setOnClickListener(this);
        data_blood_pressure_re.setOnClickListener(this);
        data_oxygen_re.setOnClickListener(this);
        data_blood_sugar_re.setOnClickListener(this);
        data_temperature_re.setOnClickListener(this);
        data_uric_acid_re.setOnClickListener(this);
        data_cholesterol_re.setOnClickListener(this);
        data_urine_routine_re.setOnClickListener(this);
        data_ecg_re.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initSwipeRefreshLayout();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        center_text.setVisibility(View.VISIBLE);
        center_text.setText("健康数据");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);
    }

    /**
     * 初始化设置下拉刷新
     */
    public void initSwipeRefreshLayout(){

        swipeLayout.setColorSchemeResources(R.color.colorSwipeLayout);
        swipeLayout.setOnRefreshListener(this);

    }

    public void onRefresh() {
        Logger.v("刷新...");
        /**
         * 模拟刷新 5秒后完成刷新(实际会是在网络请求中,届时将会取消此模拟)
         * 因为android是单线程，更新UI必须在UI线程中
         * 而Handler本身是在UI线程中的，所以直接在这里更新UI不会导致程序崩溃
         */
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task
                swipeLayout.setRefreshing(false);//完成刷新，关闭刷新
            }
        }, 5000);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.data_heart_pulse_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_blood_pressure_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_oxygen_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_blood_sugar_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_temperature_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                intent.putExtra("key",VALUE_PUBLIC);
                //startActivity(intent);
                break;

            case R.id.data_uric_acid_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_cholesterol_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_PUBLIC);
                startActivity(intent);
                break;

            case R.id.data_urine_routine_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_URINE_ROUTINE);
                startActivity(intent);
                break;

            case R.id.data_ecg_re:
                //intent.setClass(getActivity(), DataPublicTestingPresentationDetailsActivity.class);
                intent.setClass(getActivity(), DataBloodPressureDetailsActivity.class);
                //intent.putExtra("key",VALUE_ECG);
                startActivity(intent);
                break;
        }
    }
}
