package com.bjsz.app.entity.archives;

/**
 * 健康档案通用实体类
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ViewpagerArchivewEntity {

    private String aeii_left;

    private String aeii_right;

    private int aeii_right_img;

    public ViewpagerArchivewEntity(String aeii_left, String aeii_right, int aeii_right_img) {
        this.aeii_left = aeii_left;
        this.aeii_right = aeii_right;
        this.aeii_right_img = aeii_right_img;
    }

    public String getAeii_left() {
        return aeii_left;
    }

    public void setAeii_left(String aeii_left) {
        this.aeii_left = aeii_left;
    }

    public String getAeii_right() {
        return aeii_right;
    }

    public void setAeii_right(String aeii_right) {
        this.aeii_right = aeii_right;
    }

    public int getAeii_right_img() {
        return aeii_right_img;
    }

    public void setAeii_right_img(int aeii_right_img) {
        this.aeii_right_img = aeii_right_img;
    }
}
