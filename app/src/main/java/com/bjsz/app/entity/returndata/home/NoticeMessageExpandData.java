package com.bjsz.app.entity.returndata.home;

import com.google.gson.annotations.Expose;

/**
 * 通知消息JSON实体类
 * @author enmaoFu
 * @date 2017-01-18
 */
public class NoticeMessageExpandData {

    @Expose
    private String key;

    @Expose
    private String title;

    @Expose
    private String content;

    @Expose
    private String times;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
