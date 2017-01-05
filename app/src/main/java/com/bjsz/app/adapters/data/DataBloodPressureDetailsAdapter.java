package com.bjsz.app.adapters.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.entity.data.DataBloodPressureDetailsChildeEntity;
import com.bjsz.app.entity.data.DataBloodPressureDetailsGroupEntity;

import java.util.ArrayList;

/**
 * 健康数据血压详情页面ExpandableList适配器
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataBloodPressureDetailsAdapter extends BaseExpandableListAdapter {

    private ArrayList<DataBloodPressureDetailsGroupEntity> gData;
    private ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>> iData;
    private Context mContext;

    public DataBloodPressureDetailsAdapter(ArrayList<DataBloodPressureDetailsGroupEntity> gData, ArrayList<ArrayList<DataBloodPressureDetailsChildeEntity>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public DataBloodPressureDetailsGroupEntity getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public DataBloodPressureDetailsChildeEntity getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.activity_data_blood_pressure_details_groups, null);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getTitleName());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.activity_data_blood_pressure_details_childs, null);
            itemHolder = new ViewHolderItem();
            itemHolder.textChild_one = (TextView) convertView.findViewById(R.id.textChild_one);
            itemHolder.textChild_to = (TextView) convertView.findViewById(R.id.textChild_to);
            itemHolder.textChild_three = (TextView) convertView.findViewById(R.id.textChild_three);
            itemHolder.textChild_four = (TextView) convertView.findViewById(R.id.textChild_four);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.textChild_one.setText(iData.get(groupPosition).get(childPosition).getTextChild_one());
        itemHolder.textChild_to.setText(iData.get(groupPosition).get(childPosition).getTextChild_to());
        itemHolder.textChild_three.setText(iData.get(groupPosition).get(childPosition).getTextChild_three());
        itemHolder.textChild_four.setText(iData.get(groupPosition).get(childPosition).getTextChild_four());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup{
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private TextView textChild_one;
        private TextView textChild_to;
        private TextView textChild_three;
        private TextView textChild_four;
    }

}
