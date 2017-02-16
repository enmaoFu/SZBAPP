package com.bjsz.app.entity.returndata.data;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-16
 */
public class SevenTimesMoreData extends BaseData{

    @Expose
    private List<SevenTimesMoreExpandData> data;//data数据

    public List<SevenTimesMoreExpandData> getData() {
        return data;
    }

    public void setData(List<SevenTimesMoreExpandData> data) {
        this.data = data;
    }
}
