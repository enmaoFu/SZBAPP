package com.bjsz.app.activity.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.home.ArchivesMessageCoreAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.home.ArchivesMessageCoreEntity;
import com.bjsz.app.entity.returndata.home.NoticeMessageData;
import com.bjsz.app.entity.returndata.home.NoticeMessageExpandData;
import com.bjsz.app.interfaces.BaseInterface;
import com.bjsz.app.interfaces.retrofit.service.ApiService;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BaseNetworkJudge;
import com.bjsz.app.utils.BasePreference;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 消息中心页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class ArchivesMessageCoreActivity extends BaseActivity implements View.OnClickListener,BaseInterface,SwipeRefreshLayout.OnRefreshListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private SwipeRefreshLayout swipeLayout;//下拉刷新控件

    private ListView archives_msg_code_list;//listview
    private List<ArchivesMessageCoreEntity> archivesMessageCoreEntityArrayList = new ArrayList<>();//数据集
    private ArchivesMessageCoreAdapter archivesMessageCoreAdapter;//适配器

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String phoneNumber;//获取缓存的手机号

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_message_core);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        archives_msg_code_list = (ListView)findViewById(R.id.archives_msg_code_list);
        archivesMessageCoreAdapter = new ArchivesMessageCoreAdapter(this);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        archives_msg_code_list.setAdapter(archivesMessageCoreAdapter);
        left_img.setOnClickListener(this);
        archives_msg_code_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = archivesMessageCoreEntityArrayList.get(position).getKey();
                Intent intent = new Intent();
                intent.setClass(ArchivesMessageCoreActivity.this,ArchivesMessageDetailsActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);

            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        net = new BaseNetworkJudge(this);
        basePreference = new BasePreference(this);
        initSwipeRefreshLayout();
        netWorkGetNoticeMessage();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("消息中心");
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
        }
    }

    /**
     * 获取消息通知
     */
    public void netWorkGetNoticeMessage(){

        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            swipeLayout.setRefreshing(true);
            phoneNumber = basePreference.getString("phoneNumber");//用户ID
            ApiService as = initRetrofit(URL);
            Call<NoticeMessageData> call = as.getNoticeMessage(phoneNumber);
            call.enqueue(new Callback<NoticeMessageData>() {
                @Override
                public void onResponse(Call<NoticeMessageData> call, Response<NoticeMessageData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        List<NoticeMessageExpandData> nmeList = response.body().getData();
                        ArchivesMessageCoreEntity ame = null;
                        archivesMessageCoreEntityArrayList.clear();
                        for(int i = 0; i < nmeList.size(); i++){
                            ame = new ArchivesMessageCoreEntity(R.mipmap.ic_archives_msg_core_img,nmeList.get(i).getTitle(),nmeList.get(i).getContent(),
                                    nmeList.get(i).getTimes(),nmeList.get(i).getKey());
                            archivesMessageCoreEntityArrayList.add(ame);
                        }
                        archivesMessageCoreAdapter.setItems(archivesMessageCoreEntityArrayList);
                        swipeLayout.setRefreshing(false);
                    }else{
                        swipeLayout.setRefreshing(false);
                        showToast("获取通知失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<NoticeMessageData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        swipeLayout.setRefreshing(false);
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        swipeLayout.setRefreshing(false);
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        swipeLayout.setRefreshing(false);
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取通知失败"+t.getMessage());
                }
            });
        }else{
            showToast("无网络连接，获取通知失败");
        }

    }

    /**
     * 初始化设置下拉刷新
     */
    public void initSwipeRefreshLayout(){
        swipeLayout.setColorSchemeResources(R.color.colorSwipeLayout);
        swipeLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        netWorkGetNoticeMessage();
    }

}
