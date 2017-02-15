package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-14
 */
public class CategoryExpandData {

    @Expose
    private String number;

    @Expose
    private List<CategoryExpandDateData> daynumber;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<CategoryExpandDateData> getDaynumber() {
        return daynumber;
    }

    public void setDaynumber(List<CategoryExpandDateData> daynumber) {
        this.daynumber = daynumber;
    }
}
