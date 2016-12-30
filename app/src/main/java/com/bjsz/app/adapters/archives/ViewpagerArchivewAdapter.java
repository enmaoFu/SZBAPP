package com.bjsz.app.adapters.archives;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.archives.ViewpagerArchivewEntity;

/**
 * 健康档案通用适配器
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ViewpagerArchivewAdapter extends BaseAdapter<ViewpagerArchivewEntity>{

    public ViewpagerArchivewAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.viewpager_public_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView aeii_left = baseViewHolder.getView(R.id.aeii_left);
        TextView aeii_right = baseViewHolder.getView(R.id.aeii_right);
        ImageView aeii_right_img = baseViewHolder.getView(R.id.aeii_right_img);

        ViewpagerArchivewEntity viewpagerArchivewEntity = itemList.get(position);
        aeii_left.setText(viewpagerArchivewEntity.getAeii_left());
        aeii_right.setText(viewpagerArchivewEntity.getAeii_right());
        if(viewpagerArchivewEntity.getAeii_right_img() == 0){
            aeii_right_img.setVisibility(View.GONE);
        }else{
            aeii_right_img.setImageResource(viewpagerArchivewEntity.getAeii_right_img());
            aeii_right.setVisibility(View.GONE);
        }

        return convertView;
    }

}
