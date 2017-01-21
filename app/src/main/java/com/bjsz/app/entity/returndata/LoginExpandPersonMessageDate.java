package com.bjsz.app.entity.returndata;

import com.google.gson.annotations.Expose;

/**
 * 登陆返回JSON实体类 个人信息
 * @author enmaoFu
 * @date 2017-01-18
 */
public class LoginExpandPersonMessageDate {

    //private String headImage;//头像地址 暂时不从服务器获取，本地默认一个

    @Expose
    private String uid;//uid

    @Expose
    private String name;//姓名

    @Expose
    private String sex;//性别

    @Expose
    private String age;//年龄

    @Expose
    private String phoneNumber;//手机号

    @Expose
    private String identityid;//身份证号

    /*public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }*/

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }
}
