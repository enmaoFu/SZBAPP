package com.bjsz.app.entity.my;

/**
 * 我的机构实体类
 * @author enmaoFu
 * @date 2017-01-03
 */
public class MyMyMechanismEntity {

    private String my_my_mec_title;

    private String my_my_mec_content;

    private int my_my_mec_img;

    public MyMyMechanismEntity(String my_my_mec_title, String my_my_mec_content, int my_my_mec_img) {
        this.my_my_mec_title = my_my_mec_title;
        this.my_my_mec_content = my_my_mec_content;
        this.my_my_mec_img = my_my_mec_img;
    }

    public String getMy_my_mec_title() {
        return my_my_mec_title;
    }

    public void setMy_my_mec_title(String my_my_mec_title) {
        this.my_my_mec_title = my_my_mec_title;
    }

    public String getMy_my_mec_content() {
        return my_my_mec_content;
    }

    public void setMy_my_mec_content(String my_my_mec_content) {
        this.my_my_mec_content = my_my_mec_content;
    }

    public int getMy_my_mec_img() {
        return my_my_mec_img;
    }

    public void setMy_my_mec_img(int my_my_mec_img) {
        this.my_my_mec_img = my_my_mec_img;
    }
}
