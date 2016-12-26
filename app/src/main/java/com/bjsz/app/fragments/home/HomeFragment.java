package com.bjsz.app.fragments.home;

import android.view.View;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

/**
 * 首页
 * @author enmaoFu
 * @date 2016-12-26
 */
public class HomeFragment extends BaseFragment{
    @Override
    protected int bindViews() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initActionBar() {

        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(getActivity(), topView);

    }
}
