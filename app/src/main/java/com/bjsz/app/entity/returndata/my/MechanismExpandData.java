package com.bjsz.app.entity.returndata.my;

import com.google.gson.annotations.Expose;

/**
 * 获取我的机构返回JSON实体类
 * @author enmaoFu
 * @dade 2017-02-10
 */
public class MechanismExpandData {

    @Expose
    private String name;//机构名

    @Expose
    private String address;//机构地址

    @Expose
    private String id;//机构ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
