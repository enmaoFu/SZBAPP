package com.bjsz.app.adapters.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseAdapter;
import com.bjsz.app.base.BaseViewHolder;
import com.bjsz.app.entity.home.HomeGridviewOptionEntity;

/**
 * 首页的gridview选项适配器
 * @author enmaoFu
 * @date 2016-12-28
 */
public class FragmentHomeAdapter extends BaseAdapter<HomeGridviewOptionEntity>{

    public FragmentHomeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseViewHolder baseViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_home_girdview_itme,null);
        }

        baseViewHolder = BaseViewHolder.getHolder(convertView);

        ImageView home_girdview_itme_img = baseViewHolder.getView(R.id.home_girdview_itme_img);
        TextView home_girdview_itme_text = baseViewHolder.getView(R.id.home_girdview_itme_text);

        HomeGridviewOptionEntity homeGridviewOption = itemList.get(position);
        home_girdview_itme_img.setImageResource(homeGridviewOption.getItme_img());
        home_girdview_itme_text.setText(homeGridviewOption.getItme_text());

        return convertView;
    }

}
