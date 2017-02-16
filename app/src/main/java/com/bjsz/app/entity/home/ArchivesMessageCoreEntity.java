package com.bjsz.app.entity.home;

/**
 * 消息中心实体类
 * @author enmaoFu
 * @date 2017-01-03
 */
public class ArchivesMessageCoreEntity {

    private int archives_msg_core_img;

    private String archives_msg_core_title;

    private String archives_msg_core_content;

    private String archives_msg_core_date;

    private String key;

    public ArchivesMessageCoreEntity(int archives_msg_core_img, String archives_msg_core_title, String archives_msg_core_content, String archives_msg_core_date, String key) {
        this.archives_msg_core_img = archives_msg_core_img;
        this.archives_msg_core_title = archives_msg_core_title;
        this.archives_msg_core_content = archives_msg_core_content;
        this.archives_msg_core_date = archives_msg_core_date;
        this.key = key;
    }

    public int getArchives_msg_core_img() {
        return archives_msg_core_img;
    }

    public void setArchives_msg_core_img(int archives_msg_core_img) {
        this.archives_msg_core_img = archives_msg_core_img;
    }

    public String getArchives_msg_core_title() {
        return archives_msg_core_title;
    }

    public void setArchives_msg_core_title(String archives_msg_core_title) {
        this.archives_msg_core_title = archives_msg_core_title;
    }

    public String getArchives_msg_core_content() {
        return archives_msg_core_content;
    }

    public void setArchives_msg_core_content(String archives_msg_core_content) {
        this.archives_msg_core_content = archives_msg_core_content;
    }

    public String getArchives_msg_core_date() {
        return archives_msg_core_date;
    }

    public void setArchives_msg_core_date(String archives_msg_core_date) {
        this.archives_msg_core_date = archives_msg_core_date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
