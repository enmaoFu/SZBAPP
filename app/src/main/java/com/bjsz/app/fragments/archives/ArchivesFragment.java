package com.bjsz.app.fragments.archives;

import android.view.View;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

/**
 * 健康档案
 * @author enmaoFu
 * @date 2016-12-26
 */
public class ArchivesFragment extends BaseFragment {
    @Override
    protected int bindViews() {
        return R.layout.fragment_archives;
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
