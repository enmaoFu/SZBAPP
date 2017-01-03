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
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的机构页面
 * @author enmaoFu
 * @date 2016-01-03
 */
public class MyMyMechanismActivity extends BaseActivity implements OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题

    private ListView my_my_mec_list;//listview
    private List<MyMyMechanismEntity> myMyMechanismEntities = new ArrayList<>();//数据集
    private MyMyMechanismAdapter myMyMechanismAdapter;//适配器

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
     * 初始化我的机构List列表
     */
    public void initListview(){

        MyMyMechanismEntity myMyMechanismEntity = null;

        for(int i =0; i < 20; i++){

            myMyMechanismEntity = new MyMyMechanismEntity("虎溪街道卫生所","重庆市沙坪坝区大学城虎溪",R.mipmap.ic_my_my_mechanism_img);

            myMyMechanismEntities.add(myMyMechanismEntity);

        }

        myMyMechanismAdapter.setItems(myMyMechanismEntities);

    }

}
