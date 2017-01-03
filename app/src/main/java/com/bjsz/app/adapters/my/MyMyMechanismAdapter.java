package com.bjsz.app.adapters.my;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.my.MyMyMechanismEntity;

/**
 * 我的机构适配器
 * @author enmaoFu
 * @date 2017-01-03
 */
public class MyMyMechanismAdapter extends BaseAdapter<MyMyMechanismEntity>{

    public MyMyMechanismAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_my_my_mechanism_item,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        TextView my_my_mec_title = baseViewHolder.getView(R.id.my_my_mec_title);
        TextView my_my_mec_content = baseViewHolder.getView(R.id.my_my_mec_content);
        ImageView my_my_mec_img = baseViewHolder.getView(R.id.my_my_mec_img);

        MyMyMechanismEntity myMyMechanismEntity = itemList.get(position);
        my_my_mec_title.setText(myMyMechanismEntity.getMy_my_mec_title());
        my_my_mec_content.setText(myMyMechanismEntity.getMy_my_mec_content());
        my_my_mec_img.setImageResource(myMyMechanismEntity.getMy_my_mec_img());

        return convertView;
    }
}
