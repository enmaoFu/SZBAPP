package com.bjsz.app.adapters.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsGridEntity;

/**
 * 健康数据通用检测报告详情Gridview适配器
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsGridAdapter extends BaseAdapter<DataPublicTestingPresentationDetailsGridEntity>{

    public DataPublicTestingPresentationDetailsGridAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_data_public_testing_presentation_details_grid_item,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        RelativeLayout re_w = baseViewHolder.getView(R.id.re_w);
        RelativeLayout re_l = baseViewHolder.getView(R.id.re_l);
        TextView re_number = baseViewHolder.getView(R.id.re_number);
        TextView re_text = baseViewHolder.getView(R.id.re_text);

        DataPublicTestingPresentationDetailsGridEntity dataPublicTestingPresentationDetailsEntity = itemList.get(position);
        if(dataPublicTestingPresentationDetailsEntity.getIs().equals("正常")){
            re_w.setBackgroundResource(R.drawable.shape_activity_data_public_testing_item_re);
            re_l.setBackgroundResource(R.drawable.shape_activity_data_public_testing_item_re1);
        }else{
            re_w.setBackgroundResource(R.drawable.shape_activity_data_public_testing_item_re2);
            re_l.setBackgroundResource(R.drawable.shape_activity_data_public_testing_item_re3);
        }
        re_number.setText(dataPublicTestingPresentationDetailsEntity.getRe_number());
        re_text.setText(dataPublicTestingPresentationDetailsEntity.getRe_text());

        return convertView;
    }
}
