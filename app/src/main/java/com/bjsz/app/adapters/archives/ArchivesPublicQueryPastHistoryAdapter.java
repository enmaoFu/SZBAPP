package com.bjsz.app.adapters.archives;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.archives.ArchivesPublicQueryPastHistoryEntity;

/**
 * 通用查看个人病史里 既往史，家族史，遗传病史，适配器
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesPublicQueryPastHistoryAdapter extends BaseAdapter<ArchivesPublicQueryPastHistoryEntity>{

    public ArchivesPublicQueryPastHistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_archives_public_query_qast_history_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView archives_itme_left_text = baseViewHolder.getView(R.id.archives_itme_left_text);
        TextView archives_itme_right_text = baseViewHolder.getView(R.id.archives_itme_right_text);

        ArchivesPublicQueryPastHistoryEntity archivesPublicQueryPastHistoryEntity = itemList.get(position);
        archives_itme_left_text.setText(archivesPublicQueryPastHistoryEntity.getArchives_itme_left_text());
        archives_itme_right_text.setText(archivesPublicQueryPastHistoryEntity.getArchives_itme_right_text());

        return convertView;
    }
}
