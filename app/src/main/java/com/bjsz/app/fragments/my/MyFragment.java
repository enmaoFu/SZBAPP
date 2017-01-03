package com.bjsz.app.fragments.my;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.activity.my.MyMyMechanismActivity;
import com.bjsz.app.activity.my.MySetingActivity;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.views.BaseLyRoundImageView;

/**
 * 个人中心
 * @author enmaoFu
 * @date 2016-12-26
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{

    private TextView center_text;//标题栏中间标题

    private RelativeLayout my_mechanism;//我的机构
    private RelativeLayout my_seting;//设置
    private BaseLyRoundImageView my_head;//头像

    /**
     * 初始化布局
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_my;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        my_mechanism = (RelativeLayout)findViewById(R.id.my_mechanism);
        my_seting = (RelativeLayout)findViewById(R.id.my_seting);
        my_head = (BaseLyRoundImageView)findViewById(R.id.my_head);
        my_seting.setOnClickListener(this);
        my_mechanism.setOnClickListener(this);
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
        center_text.setVisibility(View.VISIBLE);
        center_text.setText("个人中心");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);

    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.my_seting:
                intent.setClass(getActivity(), MySetingActivity.class);
                startActivity(intent);
                break;
            case R.id.my_mechanism:
                intent.setClass(getActivity(), MyMyMechanismActivity.class);
                startActivity(intent);
                break;
        }
    }

}
