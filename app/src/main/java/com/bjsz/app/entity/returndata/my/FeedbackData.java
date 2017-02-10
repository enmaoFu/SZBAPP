package com.bjsz.app.entity.returndata.my;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 问题反馈返回JSON实体类
 * @author enmaoFu
 * @data 2017-02-10
 */
public class FeedbackData extends BaseData{

    @Expose
    private FeedbackExpandData data;////data数据

    public FeedbackExpandData getData() {
        return data;
    }

    public void setData(FeedbackExpandData data) {
        this.data = data;
    }
}
