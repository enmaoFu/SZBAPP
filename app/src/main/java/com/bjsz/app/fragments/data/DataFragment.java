package com.bjsz.app.fragments.data;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseFragment;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.orhanobut.logger.Logger;

/**
 * 健康数据
 * @author enmaoFu
 * @date 2016-12-26
 */
public class DataFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private TextView center_text;//标题栏中间标题

    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    @Override
    protected int bindViews() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
    }

    @Override
    protected void initData() {
        initSwipeRefreshLayout();
    }

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
}
