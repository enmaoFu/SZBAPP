package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取检测报告返回JSON实体类 二级栏目，每次测量
 * @author enmaoFu
 * @date 2017-02-14
 */
public class CategoryExpandDateTimeData {

    @Expose
    private String key;

    @Expose
    private String date;

    @Expose
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
