package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取检测报告详情返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-15
 */
public class CategoryDetailsExpandData {

    @Expose
    private String times;

    @Expose
    private List<CategoryDetailsExpandGridViewData> gridview;

    @Expose
    private List<CategoryDetailsExpandListViewData> listview;

    @Expose
    private String reminder;

    @Expose
    private String result;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public List<CategoryDetailsExpandGridViewData> getGridview() {
        return gridview;
    }

    public void setGridview(List<CategoryDetailsExpandGridViewData> gridview) {
        this.gridview = gridview;
    }

    public List<CategoryDetailsExpandListViewData> getListview() {
        return listview;
    }

    public void setListview(List<CategoryDetailsExpandListViewData> listview) {
        this.listview = listview;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
