package com.bjsz.app.entity.returndata;

/**
 * 登陆返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class LoginData extends BaseData{

    private LoginExpandDate data;//data数据

    public LoginExpandDate getData() {
        return data;
    }

    public void setData(LoginExpandDate data) {
        this.data = data;
    }
}
