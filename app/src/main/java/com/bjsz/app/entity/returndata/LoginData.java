package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 登陆返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class LoginData extends BaseData{

    @Expose
    private LoginExpandDate data;//data数据

    public LoginExpandDate getData() {
        return data;
    }

    public void setData(LoginExpandDate data) {
        this.data = data;
    }
}
