package com.bjsz.app.entity.returndata.home;


import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 通知消息详情JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class NoticeMessageDetailsData extends BaseData{

    @Expose
    private String data;//data数据

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
