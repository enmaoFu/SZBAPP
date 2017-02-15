package com.bjsz.app.entity.data;

/**
 * 血压详情页面ExpandableList二级目录实体类
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataBloodPressureDetailsChildeEntity {

    private String key;

    private String textChild_left;

    private String textChild_right;

    public DataBloodPressureDetailsChildeEntity(String key, String textChild_left, String textChild_right) {
        this.key = key;
        this.textChild_left = textChild_left;
        this.textChild_right = textChild_right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTextChild_left() {
        return textChild_left;
    }

    public void setTextChild_left(String textChild_left) {
        this.textChild_left = textChild_left;
    }

    public String getTextChild_right() {
        return textChild_right;
    }

    public void setTextChild_right(String textChild_right) {
        this.textChild_right = textChild_right;
    }
}
