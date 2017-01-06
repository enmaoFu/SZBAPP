package com.bjsz.app.adapters.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.data.DataHealthyAnalysisEntity;

/**
 * 健康数据健康分析页面时光轴适配器,以及时光轴更多页面通用
 * @author enmaoFu
 * @date 2017-01-06
 */
public class DataHealthyAnalysisAdapter extends BaseAdapter<DataHealthyAnalysisEntity>{

    public DataHealthyAnalysisAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_data_healthy_analysis_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView data_healthy_analysis_itme_date_one = baseViewHolder.getView(R.id.data_healthy_analysis_itme_date_one);
        TextView data_healthy_analysis_itme_date_to = baseViewHolder.getView(R.id.data_healthy_analysis_itme_date_to);
        TextView data_healthy_analysis_itme_date_three = baseViewHolder.getView(R.id.data_healthy_analysis_itme_date_three);
        TextView data_healthy_analysis_itme_text = baseViewHolder.getView(R.id.data_healthy_analysis_itme_text);

        DataHealthyAnalysisEntity dataHealthyAnalysisEntity = itemList.get(position);
        data_healthy_analysis_itme_date_one.setText(dataHealthyAnalysisEntity.getData_healthy_analysis_itme_date_one());
        data_healthy_analysis_itme_date_to.setText(dataHealthyAnalysisEntity.getData_healthy_analysis_itme_date_to());
        data_healthy_analysis_itme_text.setText(dataHealthyAnalysisEntity.getData_healthy_analysis_itme_text());
        data_healthy_analysis_itme_date_three.setText(dataHealthyAnalysisEntity.getData_healthy_analysis_itme_date_three());

        return convertView;
    }
}
