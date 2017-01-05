package com.bjsz.app.entity.data;

/**
 * 血压详情页面ExpandableList一级目录实体类
 * @author enmaoFu
 * @date 2017-01-05
 */
public class DataBloodPressureDetailsGroupEntity {

    private String titleName;

    public DataBloodPressureDetailsGroupEntity(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}

