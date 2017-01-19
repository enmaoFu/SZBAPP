package com.bjsz.app.entity.returndata;

/**
 * 返回JSON基础实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class BaseData {

    private int status;//返回状态码

    private String msg;//返回信息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
