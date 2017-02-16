package com.bjsz.app.entity.returndata.home;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 通知消息JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class NoticeMessageData extends BaseData{

    @Expose
    private List<NoticeMessageExpandData> data;//data数据

    public List<NoticeMessageExpandData> getData() {
        return data;
    }

    public void setData(List<NoticeMessageExpandData> data) {
        this.data = data;
    }
}
