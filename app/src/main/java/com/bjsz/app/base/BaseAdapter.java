package com.bjsz.app.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础的 BaseAdapter 适配器封装类
 * @author enmaoFu
 * @date 2016-12-23
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> itemList = new ArrayList<T>();

    public BaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 判断数据是否为空
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    /**
     * 在原有的数据上添加新数据
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        if(this.itemList== null) {
            this.itemList = new ArrayList<>();
        }
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     * @param itemList
     */
    public void setItems(List<T> itemList) {
        this.itemList = itemList;
        if(this.itemList== null) {
            this.itemList = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        if (itemList!=null){
            itemList=new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup parent);

}
