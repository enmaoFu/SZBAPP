package com.bjsz.app.activity.archives;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.archives.ArchivesPublicQueryPastHistoryAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.archives.ArchivesPublicQueryPastHistoryEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用查看个人病里 既往史，家族史，遗传病史，页面
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesPublicQueryPastHistoryActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边添加

    private ListView aaqqh_list;//listview
    private List<ArchivesPublicQueryPastHistoryEntity> archivesPublicQueryPastHistoryEntityArrayList = new ArrayList<>();//数据集
    private ArchivesPublicQueryPastHistoryAdapter archivesPublicQueryPastHistoryAdapter;//适配器

    private String keyValue;//接收传过来的值，便于判断是点击的既往史，家族史，遗传病史的哪一个

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
        Bundle bundle = this.getIntent().getExtras();
        keyValue = bundle.getString("key");
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
        switch (keyValue){
            case "jws":
                center_text.setText("既往史");
                break;
            case "jzs":
                center_text.setText("家族史");
                break;
            case "ycbs":
                center_text.setText("遗传病史");
                break;
        }
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
                intent.setClass(this,ArchivesPublicAddPastHistoryActivity.class);
                intent.putExtra("key",keyValue);
                startActivity(intent);
                break;

        }
    }

    /**
     * 初始化Liste列表 （既往史，家族史，遗传病史）
     */
    public void initList(){

        archivesPublicQueryPastHistoryEntityArrayList.clear();

        ArchivesPublicQueryPastHistoryEntity apqpEntity = null;

        switch (keyValue){
            case "jws":
                for(int i = 0; i < 20; i++){

                    apqpEntity = new ArchivesPublicQueryPastHistoryEntity("高血压","2016-12-30");

                    archivesPublicQueryPastHistoryEntityArrayList.add(apqpEntity);

                }
                break;
            case "jzs":
                for(int i = 0; i < 20; i++){

                    apqpEntity = new ArchivesPublicQueryPastHistoryEntity("父亲","高血压、糖尿病");

                    archivesPublicQueryPastHistoryEntityArrayList.add(apqpEntity);

                }
                break;
            case "ycbs":
                for(int i = 0; i < 20; i++){

                    apqpEntity = new ArchivesPublicQueryPastHistoryEntity("遗传病况","色盲");

                    archivesPublicQueryPastHistoryEntityArrayList.add(apqpEntity);

                }
                break;
        }

        archivesPublicQueryPastHistoryAdapter.setItems(archivesPublicQueryPastHistoryEntityArrayList);

    }

}
