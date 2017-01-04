package com.bjsz.app.activity.data;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bjsz.app.R;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 血压详情页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataBloodPressureDetailsActivity extends ExpandableListActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private TextView right_text;//标题栏右边分析

    /**
     * 创建一级条目容器
     */
    List<Map<String, String>> gruops = new ArrayList<Map<String, String>>();
    /**
     * 存放内容, 以便显示在列表中
     */
    List<List<Map<String, String>>> childs = new ArrayList<List<Map<String, String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_blood_pressure_details);
        initView();
        initActionBar();
        setListData();
    }

    /**
     * 初始化组件
     */
    public void initView(){

        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_text = (TextView)findViewById(R.id.right_text);
        left_img.setOnClickListener(this);

    }

    /**
     * 初始化标题栏
     */
    public void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_text.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("检测报告");
        right_text.setText("分析");
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
                finish();
                overridePendingTransition(0, R.anim.base_frame_anim_back_right_out);
                break;
        }
    }

    /**
     * 设置列表内容
     */
    public void setListData() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //图标设置在右边
        getExpandableListView().setIndicatorBounds(dm.widthPixels-115, dm.widthPixels); // 设置指示图标的位置

        // 创建二个一级条目标题
        Map<String, String> title_1 = new HashMap<String, String>();
        Map<String, String> title_2 = new HashMap<String, String>();
        Map<String, String> title_3 = new HashMap<String, String>();
        title_1.put("group", "林炳文");
        title_2.put("group", "文炳林");
        gruops.add(title_1);
        gruops.add(title_2);

        // 创建二级条目内容
        // 内容一
        Map<String, String> title_1_content_1 = new HashMap<String, String>();
        Map<String, String> title_1_content_2 = new HashMap<String, String>();
        Map<String, String> title_1_content_3 = new HashMap<String, String>();
        title_1_content_1.put("child", "工人");
        title_1_content_2.put("child", "学生");
        title_1_content_3.put("child", "农民");

        List<Map<String, String>> childs_1 = new ArrayList<Map<String, String>>();
        childs_1.add(title_1_content_1);
        childs_1.add(title_1_content_2);
        childs_1.add(title_1_content_3);

        // 内容二
        Map<String, String> title_2_content_1 = new HashMap<String, String>();
        Map<String, String> title_2_content_2 = new HashMap<String, String>();
        Map<String, String> title_2_content_3 = new HashMap<String, String>();
        title_2_content_1.put("child", "猩猩");
        title_2_content_2.put("child", "老虎");
        title_2_content_3.put("child", "狮子");
        List<Map<String, String>> childs_2 = new ArrayList<Map<String, String>>();
        childs_2.add(title_2_content_1);
        childs_2.add(title_2_content_2);
        childs_2.add(title_2_content_3);

        childs.add(childs_1);
        childs.add(childs_2);

        /**
         * 创建ExpandableList的Adapter容器 参数: 1.上下文 2.一级集合 3.一级样式文件 4. 一级条目键值
         * 5.一级显示控件名 6. 二级集合 7. 二级样式 8.二级条目键值 9.二级显示控件名
         *
         */
        SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
                this, gruops, R.layout.activity_data_blood_pressure_details_groups, new String[] { "group" },
                new int[] { R.id.textGroup }, childs, R.layout.activity_data_blood_pressure_details_childs,
                new String[] { "child" }, new int[] { R.id.textChild });
        // 加入列表
        setListAdapter(sela);
    }

    /**
     * 列表内容按下
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(
                DataBloodPressureDetailsActivity.this,
                "您选择了"
                        + gruops.get(groupPosition).toString()
                        + "子编号"
                        + childs.get(groupPosition).get(childPosition)
                        .toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this, DataPublicTestingPresentationDetailsActivity.class);
        startActivity(intent);
        return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }

    /**
     * 二级标题按下
     */
    @Override
    public boolean setSelectedChild(int groupPosition, int childPosition,
                                    boolean shouldExpandGroup) {
        return super.setSelectedChild(groupPosition, childPosition,
                shouldExpandGroup);
    }

    /**
     * 一级标题按下
     */
    @Override
    public void setSelectedGroup(int groupPosition) {
        super.setSelectedGroup(groupPosition);
    }

}
