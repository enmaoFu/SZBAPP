package com.bjsz.app.entity.data;

/**
 * 血压详情页面ExpandableList二级目录实体类
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataBloodPressureDetailsChildeEntity {

    private String textChild_one;

    private String textChild_to;

    private String textChild_three;

    private String textChild_four;

    public DataBloodPressureDetailsChildeEntity(String textChild_one, String textChild_to, String textChild_three, String textChild_four) {
        this.textChild_one = textChild_one;
        this.textChild_to = textChild_to;
        this.textChild_three = textChild_three;
        this.textChild_four = textChild_four;
    }

    public String getTextChild_one() {
        return textChild_one;
    }

    public void setTextChild_one(String textChild_one) {
        this.textChild_one = textChild_one;
    }

    public String getTextChild_to() {
        return textChild_to;
    }

    public void setTextChild_to(String textChild_to) {
        this.textChild_to = textChild_to;
    }

    public String getTextChild_three() {
        return textChild_three;
    }

    public void setTextChild_three(String textChild_three) {
        this.textChild_three = textChild_three;
    }

    public String getTextChild_four() {
        return textChild_four;
    }

    public void setTextChild_four(String textChild_four) {
        this.textChild_four = textChild_four;
    }
}
