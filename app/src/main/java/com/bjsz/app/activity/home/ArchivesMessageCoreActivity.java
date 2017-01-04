package com.bjsz.app.activity.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.home.ArchivesMessageCoreAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.home.ArchivesMessageCoreEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心页面
 * @author enmaoFu
 * @date 2017-01-03
 */
public class ArchivesMessageCoreActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private ListView archives_msg_code_list;//listview
    private List<ArchivesMessageCoreEntity> archivesMessageCoreEntityArrayList = new ArrayList<>();//数据集
    private ArchivesMessageCoreAdapter archivesMessageCoreAdapter;//适配器

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
        archives_msg_code_list.setAdapter(archivesMessageCoreAdapter);
        left_img.setOnClickListener(this);
        archives_msg_code_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(ArchivesMessageCoreActivity.this,ArchivesMessageDetailsActivity.class);
                startActivity(intent);

            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initListview();
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
     * 初始化消息列表
     */
    public void initListview(){

        ArchivesMessageCoreEntity ame = null;

        for(int i = 0; i < 20; i++){

            ame = new ArchivesMessageCoreEntity(R.mipmap.ic_archives_msg_core_img,"您有新的测量结果等待确认","血压 测量结果：89/168mmHg","14:30");

            archivesMessageCoreEntityArrayList.add(ame);

        }

        archivesMessageCoreAdapter.setItems(archivesMessageCoreEntityArrayList);

    }

}
