package com.bjsz.app.activity.archives;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.archives.ArchivesPublicQueryPastHistoryAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.archives.ArchivesPublicQueryPastHistoryEntity;
import com.bjsz.app.entity.returndata.archives.MedicalhistoryData;
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
 * 查看个人病里 既往史页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesMedicalhistoryQueryPastHistoryActivity extends BaseActivity implements View.OnClickListener,BaseInterface{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边添加

    private ListView aaqqh_list;//listview
    private List<ArchivesPublicQueryPastHistoryEntity> archivesPublicQueryPastHistoryEntityArrayList = new ArrayList<>();//数据集
    private ArchivesPublicQueryPastHistoryAdapter archivesPublicQueryPastHistoryAdapter;//适配器

    private RelativeLayout query_ycbs_title;//遗传病史需要的头部标题

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储
    private String uid;//本地获取的uid

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_archives_public_query_qast_history);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        aaqqh_list = (ListView)findViewById(R.id.aaqqh_list);
        aaqqh_list.setDivider(new ColorDrawable(Color.parseColor("#F4F8F9")));
        aaqqh_list.setDividerHeight(2);
        query_ycbs_title = (RelativeLayout)findViewById(R.id.query_ycbs_title);
        archivesPublicQueryPastHistoryAdapter = new ArchivesPublicQueryPastHistoryAdapter(this);
        aaqqh_list.setAdapter(archivesPublicQueryPastHistoryAdapter);
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
        initList();
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
        right_text.setText("添加");
        center_text.setText("既往史");
        View topView = findViewById(R.id.lin);
        BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
            case R.id.right_text:
                intent.setClass(this,ArchivestMedicalhistoryAddPastHistoryActivity.class);
                startActivityForResult(intent,1);
                break;

        }
    }

    /**
     * 添加既往史回调函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            String illness = data.getExtras().getString("illness");
            String cure = data.getExtras().getString("cure");
            ArchivesPublicQueryPastHistoryEntity apqpEntity = null;
            apqpEntity = new ArchivesPublicQueryPastHistoryEntity(illness,cure);
            archivesPublicQueryPastHistoryEntityArrayList.add(apqpEntity);
            archivesPublicQueryPastHistoryAdapter.notifyDataSetChanged();
            showToast("添加既往史成功");
        }
    }

    /**
     * 初始化Liste列表 （既往史，家族史，遗传病史）
     */
    public void initList(){

        NetworkGetMedicalhistory();

    }

    /**
     * 获取既往史信息
     */
    public void NetworkGetMedicalhistory(){
        boolean flags = net.isNetworkConnected(this);
        if(flags == true){
            uid = basePreference.getString("uid");//uid
            ApiService as = initRetrofit(URL);
            Call<MedicalhistoryData> call = as.getMedicalhistory(uid,"jw");
            call.enqueue(new Callback<MedicalhistoryData>() {
                @Override
                public void onResponse(Call<MedicalhistoryData> call, Response<MedicalhistoryData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){

                        archivesPublicQueryPastHistoryEntityArrayList.clear();
                        ArchivesPublicQueryPastHistoryEntity apqpEntity = null;
                        for(int i = 0; i < response.body().getData().size(); i++){
                            apqpEntity = new ArchivesPublicQueryPastHistoryEntity(response.body().getData().get(i).getJws(),
                                    response.body().getData().get(i).getZy());
                            archivesPublicQueryPastHistoryEntityArrayList.add(apqpEntity);
                        }
                        archivesPublicQueryPastHistoryAdapter.setItems(archivesPublicQueryPastHistoryEntityArrayList);

                    }else{
                        showToast("获取既往史失败，请重试");
                    }
                }

                @Override
                public void onFailure(Call<MedicalhistoryData> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        showToast("网络超时，请检查您的网络状态");
                    } else if (t instanceof ConnectException) {
                        showToast("网络中断，请检查您的网络状态");
                    } else {
                        showToast("服务器发生错误，请等待修复");
                    }
                    Logger.v("获取既往史信息失败"+t.getMessage());
                }
            });
        }else{
            showToast("获取既往史失败，请检查网络并下拉刷新");
        }
    }

}
