package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 生活习惯返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-22
 */
public class LifeHabitExpandData {

    @Expose
    private String title;//项目名称

    @Expose
    private String content;//值

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
