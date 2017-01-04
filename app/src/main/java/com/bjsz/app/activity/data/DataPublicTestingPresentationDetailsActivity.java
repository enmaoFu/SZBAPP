package com.bjsz.app.activity.data;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.adapters.data.DataPublicTestingPresentationDetailsGridAdapter;
import com.bjsz.app.adapters.data.DataPublicTestingPresentationDetailsListAdapter;
import com.bjsz.app.base.BaseActivity;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsGridEntity;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsListEntity;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.views.BaseLyGridView;
import com.bjsz.app.views.BaseLyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康数据通用检测报告详情页面
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsActivity extends BaseActivity implements View.OnClickListener{

    private ImageView left_img;//标题栏左边返回
    private TextView center_text;//标题栏中间标题
    private ImageView right_img;//标题栏右边更多

    private BaseLyGridView data_public_testing_gridview;//gridview
    private List<DataPublicTestingPresentationDetailsGridEntity> dataPublicTestingPresentationDetailsEntityArrayList = new ArrayList<>();//数据集
    private DataPublicTestingPresentationDetailsGridAdapter dataPublicTestingPresentationDetailsAdapter;//适配器

    private BaseLyListView data_public_testing_listview;//listview
    private List<DataPublicTestingPresentationDetailsListEntity> dataPublicTestingPresentationDetailsListEntityArrayList = new ArrayList<>();//数据集
    private DataPublicTestingPresentationDetailsListAdapter dataPublicTestingPresentationDetailsListAdapter;//适配器

    private RelativeLayout data_public_testing_top;//横向图片

    private String keyValue;//接收传过来的值，便于判断是点击的通用，尿常规，心电的哪一个
    /**
     * 传递给健康数据通用检测报告详情页面
     * 默认为1 表示通用的
     * 2 表示尿常规
     * 3 表示心电
     */
    private static final String VALUE_PUBLIC = "1";
    private static final String VALUE_URINE_ROUTINE = "2";
    private static final String VALUE_ECG = "3";

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_data_public_testing_presentation_details);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        left_img = (ImageView)findViewById(R.id.left_img);
        center_text = (TextView)findViewById(R.id.center_text);
        right_img = (ImageView)findViewById(R.id.right_img);
        data_public_testing_gridview = (BaseLyGridView)findViewById(R.id.data_public_testing_gridview);
        dataPublicTestingPresentationDetailsAdapter = new DataPublicTestingPresentationDetailsGridAdapter(this);
        data_public_testing_gridview.setAdapter(dataPublicTestingPresentationDetailsAdapter);
        data_public_testing_listview = (BaseLyListView)findViewById(R.id.data_public_testing_listview);
        dataPublicTestingPresentationDetailsListAdapter = new DataPublicTestingPresentationDetailsListAdapter(this);
        data_public_testing_listview.setAdapter(dataPublicTestingPresentationDetailsListAdapter);
        data_public_testing_top = (RelativeLayout)findViewById(R.id.data_public_testing_top);
        left_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        initGridview();
        initListview();
        /*Bundle bundle = this.getIntent().getExtras();
        keyValue = bundle.getString("key");
        switch (keyValue){
            case VALUE_PUBLIC:
                initGridview();
                initListview();
                break;
            case VALUE_URINE_ROUTINE:
                initGridview();
                initListview();
                break;
            case VALUE_ECG:
                data_public_testing_top.setVisibility(View.VISIBLE);
                initGridview();
                initListview();
                break;
        }*/

    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        left_img.setVisibility(View.VISIBLE);
        center_text.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_left_img);
        center_text.setText("检测报告");
        right_img.setImageResource(R.mipmap.ic_right_more_img);
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
     * 初始化gridview测量结果
     */
    public void initGridview(){

        DataPublicTestingPresentationDetailsGridEntity dataPublicTestingPresentationDetailsEntity = null;

        for(int i = 0; i < 9; i++){

            dataPublicTestingPresentationDetailsEntity = new DataPublicTestingPresentationDetailsGridEntity("正常","118","高压mmHg");

            dataPublicTestingPresentationDetailsEntityArrayList.add(dataPublicTestingPresentationDetailsEntity);

        }

        dataPublicTestingPresentationDetailsAdapter.setItems(dataPublicTestingPresentationDetailsEntityArrayList);

    }

    /**
     * 初始化Listview测量详情
     */
    public void initListview(){

        DataPublicTestingPresentationDetailsListEntity dataPublicTestingPresentationDetailsListEntity = null;

        for(int i = 0; i < 7; i++){

            dataPublicTestingPresentationDetailsListEntity = new DataPublicTestingPresentationDetailsListEntity("舒张压","110","90-120");

            dataPublicTestingPresentationDetailsListEntityArrayList.add(dataPublicTestingPresentationDetailsListEntity);

        }

        dataPublicTestingPresentationDetailsListAdapter.setItems(dataPublicTestingPresentationDetailsListEntityArrayList);

    }

}
