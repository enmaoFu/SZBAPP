package com.bjsz.app.entity.returndata.my;

import com.google.gson.annotations.Expose;

/**
 * 问题反馈返回JSON实体类
 * @author enmaoFu
 * @data 2017-02-10
 */
public class FeedbackExpandData {

    @Expose
    private int code;//状态码

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
