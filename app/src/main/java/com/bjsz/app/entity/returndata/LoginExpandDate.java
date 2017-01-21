package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 登陆返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class LoginExpandDate {

    @Expose
    private LoginExpandPersonMessageDate personMessage;//个人信息

    @Expose
    private LoginExpandMeasureNumberDate measureNumber;//测量信息

    private String healthyKey;//获取数据key

    private String phoneNumber;//手机号码

    public LoginExpandPersonMessageDate getPersonMessage() {
        return personMessage;
    }

    public void setPersonMessage(LoginExpandPersonMessageDate personMessage) {
        this.personMessage = personMessage;
    }

    public LoginExpandMeasureNumberDate getMeasureNumber() {
        return measureNumber;
    }

    public void setMeasureNumber(LoginExpandMeasureNumberDate measureNumber) {
        this.measureNumber = measureNumber;
    }

    public String getHealthyKey() {
        return healthyKey;
    }

    public void setHealthyKey(String healthyKey) {
        this.healthyKey = healthyKey;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
