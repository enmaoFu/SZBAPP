package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 获取验证码返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class CodeReturnExpandData {

    @Expose
    private String code;//验证码

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
