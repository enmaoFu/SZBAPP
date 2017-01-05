package com.bjsz.app.activity.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.views.BaseRoundProgressBarView;

/**
 * 健康数据健康分析页面
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataHealthyAnalysisActivity extends BaseActivity implements View.OnClickListener {

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private BaseRoundProgressBarView data_bar_view;//自定义圆环

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_healthy_analysis);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView) findViewById(R.id.left_img);
        center_text = (TextView) findViewById(R.id.center_text);
        data_bar_view = (BaseRoundProgressBarView) findViewById(R.id.data_bar_view);
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        data_bar_view.setProgress(80);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("健康分析");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_img:
                backView();
                break;
        }
    }

}
