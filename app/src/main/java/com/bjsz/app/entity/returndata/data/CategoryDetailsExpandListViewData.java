package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取检测报告详情返回JSON实体类 listview
 * @author enmaoFu
 * @date 2017-02-15
 */
public class CategoryDetailsExpandListViewData {

    @Expose
    private String number;

    @Expose
    private String title;

    @Expose
    private String range;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
