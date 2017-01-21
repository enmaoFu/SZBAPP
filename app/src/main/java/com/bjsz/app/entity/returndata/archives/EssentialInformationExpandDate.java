package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 基本信息返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-21
 */
public class EssentialInformationExpandDate {

    @Expose
    private String truename;//姓名

    @Expose
    private String cardnum;//身份证号

    @Expose
    private String sex;//性别

    @Expose
    private String birthday;//出生日期

    @Expose
    private String height;//身高

    @Expose
    private String weight;//体重

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
