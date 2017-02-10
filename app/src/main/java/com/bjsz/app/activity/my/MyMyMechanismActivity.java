package com.bjsz.app.activity.my;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.my.MyMyMechanismAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.my.MyMyMechanismEntity;
import com.bjsz.app.entity.returndata.my.MechanismData;
import com.bjsz.app.entity.returndata.my.MechanismExpandData;
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
 * 我的机构页面
 * @author enmaoFu
 * @date 2016-01-03
 */
public class MyMyMechanismActivity extends BaseActivity implements OnClickListener,BaseInterface{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private ListView my_my_mec_list;//listview
    private List<MyMyMechanismEntity> myMyMechanismEntities = new ArrayList<>();//数据集
    private MyMyMechanismAdapter myMyMechanismAdapter;//适配器

    private BaseNetworkJudge net;//网络判断
    private BasePreference basePreference;//本地存储

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_my_my_mechanism);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        my_my_mec_list = (ListView)findViewById(R.id.my_my_mec_list);
        myMyMechanismAdapter = new MyMyMechanismAdapter(this);
        my_my_mec_list.setAdapter(myMyMechanismAdapter);
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        basePreference = new BasePreference(MyMyMechanismActivity.this);
        net = new BaseNetworkJudge(MyMyMechanismActivity.this);
        netWorkMyChanism();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("我的机构");
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
     * 获取我的机构
     */
    public void netWorkMyChanism(){
        boolean flag = net.isNetworkConnected(this);
        if(flag == true){
            baseShowDialog();
            String numberPhoen = basePreference.getString("phoneNumber");
            ApiService as = initRetrofit(URL);
            Call<MechanismData> call = as.getMyMechanism(numberPhoen);
            call.enqueue(new Callback<MechanismData>() {
                @Override
                public void onResponse(Call<MechanismData> call, Response<MechanismData> response) {
                    int status = response.body().getStatus();
                    if(status == 0){
                        List<MechanismExpandData> data = response.body().getData();
                        if(data.isEmpty()){
                            baseHideDialog();
                            showToast("暂时没有已检测过的机构");
                        }else{
                            baseHideDialog();
                            MyMyMechanismEntity myMyMechanismEntity = null;
                            for(int i = 0; i < data.size(); i++){
                                myMyMechanismEntity = new MyMyMechanismEntity(data.get(i).getName(),data.get(i).getAddress(),R.mipmap.ic_my_my_mechanism_img);
                                myMyMechanismEntities.add(myMyMechanismEntity);
                            }
                            myMyMechanismAdapter.setItems(myMyMechanismEntities);
                        }
                    }else{
                        baseHideDialog();
                        showToast("获取我的机构失败");
                    }
                }

                @Override
                public void onFailure(Call<MechanismData> call, Throwable t) {
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
                    Logger.v("我的机构获取失败"+t.getMessage());
                }
            });
        }else{
            showToast("获取我的机构失败，请检查网络并下拉刷新");
        }
    }

}
