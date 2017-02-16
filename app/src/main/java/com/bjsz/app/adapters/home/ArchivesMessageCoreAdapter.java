package com.bjsz.app.adapters.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.home.ArchivesMessageCoreEntity;

/**
 * 消息中心适配器
 * @author enmaoFu
 * @date 2017-01-03
 */
public class ArchivesMessageCoreAdapter extends BaseAdapter<ArchivesMessageCoreEntity>{

    public ArchivesMessageCoreAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_archives_message_core_item,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView archives_msg_core_img = baseViewHolder.getView(R.id.archives_msg_core_img);
        TextView archives_msg_core_title = baseViewHolder.getView(R.id.archives_msg_core_title);
        TextView archives_msg_core_content = baseViewHolder.getView(R.id.archives_msg_core_content);
        TextView archives_msg_core_date = baseViewHolder.getView(R.id.archives_msg_core_date);
        TextView key = baseViewHolder.getView(R.id.key);

        ArchivesMessageCoreEntity archivesMessageCoreEntity = itemList.get(position);
        archives_msg_core_img.setImageResource(archivesMessageCoreEntity.getArchives_msg_core_img());
        archives_msg_core_title.setText(archivesMessageCoreEntity.getArchives_msg_core_title());
        archives_msg_core_content.setText(archivesMessageCoreEntity.getArchives_msg_core_content());
        archives_msg_core_date.setText(archivesMessageCoreEntity.getArchives_msg_core_date());
        key.setText(archivesMessageCoreEntity.getKey());

        return convertView;
    }
}
