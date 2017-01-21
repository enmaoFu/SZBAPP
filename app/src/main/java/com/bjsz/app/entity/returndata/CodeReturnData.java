package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 获取验证码返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class CodeReturnData extends BaseData{

    @Expose
    private CodeReturnExpandData data;//data数据

    public CodeReturnExpandData getData() {
        return data;
    }

    public void setData(CodeReturnExpandData data) {
        this.data = data;
    }
}
