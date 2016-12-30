package com.bjsz.app.entity.archives;

/**
 * 通用查看个人病史里 既往史，家族史，遗传病史，实体类
 * @author enmaoFu
 * @date 2016-12-30
 */
public class ArchivesPublicQueryPastHistoryEntity {

    private String archives_itme_left_text;

    private String archives_itme_right_text;

    public ArchivesPublicQueryPastHistoryEntity(String archives_itme_left_text, String archives_itme_right_text) {
        this.archives_itme_left_text = archives_itme_left_text;
        this.archives_itme_right_text = archives_itme_right_text;
    }

    public String getArchives_itme_left_text() {
        return archives_itme_left_text;
    }

    public void setArchives_itme_left_text(String archives_itme_left_text) {
        this.archives_itme_left_text = archives_itme_left_text;
    }

    public String getArchives_itme_right_text() {
        return archives_itme_right_text;
    }

    public void setArchives_itme_right_text(String archives_itme_right_text) {
        this.archives_itme_right_text = archives_itme_right_text;
    }
}
