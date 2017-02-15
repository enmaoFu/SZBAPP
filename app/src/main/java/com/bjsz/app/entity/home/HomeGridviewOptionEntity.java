package com.bjsz.app.entity.home;

/**
 * 首页的gridview选项实体类
 * @author enmaoFu
 * @date 2016-12-28
 */
public class HomeGridviewOptionEntity {

    private int itme_img;

    private String itme_text;

    private String key;

    public HomeGridviewOptionEntity(int itme_img, String itme_text, String key) {
        this.itme_img = itme_img;
        this.itme_text = itme_text;
        this.key = key;
    }

    public int getItme_img() {
        return itme_img;
    }

    public void setItme_img(int itme_img) {
        this.itme_img = itme_img;
    }

    public String getItme_text() {
        return itme_text;
    }

    public void setItme_text(String itme_text) {
        this.itme_text = itme_text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
