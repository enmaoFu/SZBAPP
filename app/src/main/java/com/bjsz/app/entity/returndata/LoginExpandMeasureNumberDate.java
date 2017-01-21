package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 登陆返回JSON实体类 测量数据
 * @author enmaoFu
 * @date 2017-01-18
 */
public class LoginExpandMeasureNumberDate {

    @Expose
    private String total;//总测量条数

    @Expose
    private String today;//进入测量条数

    @Expose
    private String abnormal;//异常测量条数

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(String abnormal) {
        this.abnormal = abnormal;
    }

}
