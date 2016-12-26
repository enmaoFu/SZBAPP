package com.bjsz.app.base;

import android.util.SparseArray;
import android.view.View;

/**
 * 用于复用listView的holder
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseViewHolder {

    private final SparseArray<View> views;
    private View convertView;


    private BaseViewHolder(View convertView) {
        this.views = new SparseArray<View>();
        this.convertView = convertView;
        convertView.setTag(this);
    }

    public static BaseViewHolder getHolder(View convertView) {
        if (convertView.getTag() == null) {
            return new BaseViewHolder(convertView);
        }
        BaseViewHolder existedHolder = (BaseViewHolder) convertView.getTag();
        return existedHolder;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

}
