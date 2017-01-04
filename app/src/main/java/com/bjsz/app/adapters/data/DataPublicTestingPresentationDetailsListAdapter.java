package com.bjsz.app.adapters.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.data.DataPublicTestingPresentationDetailsListEntity;

/**
 * 健康数据通用检测报告详情Listview适配器
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsListAdapter extends BaseAdapter<DataPublicTestingPresentationDetailsListEntity>{

    public DataPublicTestingPresentationDetailsListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_data_public_testing_presentation_detail_list_item,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView data_public_list_s = baseViewHolder.getView(R.id.data_public_list_s);
        TextView data_public_list_number = baseViewHolder.getView(R.id.data_public_list_number);
        TextView data_public_list_f = baseViewHolder.getView(R.id.data_public_list_f);

        DataPublicTestingPresentationDetailsListEntity dataPublicTestingPresentationDetailsListEntity = itemList.get(position);
        data_public_list_s.setText(dataPublicTestingPresentationDetailsListEntity.getData_public_list_s());
        data_public_list_number.setText(dataPublicTestingPresentationDetailsListEntity.getData_public_list_number());
        data_public_list_f.setText(dataPublicTestingPresentationDetailsListEntity.getData_public_list_f());

        return convertView;
    }
}
