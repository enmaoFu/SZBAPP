package com.bjsz.app.fragments.my;

import android.view.View;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

/**
 * 个人中心
 * @author enmaoFu
 * @date 2016-12-26
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{

    private TextView center_text;//标题栏中间标题

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

    }

}
