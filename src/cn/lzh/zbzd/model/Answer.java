package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Answer implements Serializable {
    
    /**
     * create table answer(
     * pk_id bigint unsigned auto_increment,
     * gmt_create datetime not null,
     * gmt_modified datetime not null,
     * content text not null,
     * is_anonymous tinyint unsigned default 0,
     * question_id bigint unsigned,
     * user_id bigint unsigned,
     * primary key (pk_id),
     * foreign key (question_id) references question (pk_id),
     * foreign key(user_id) references user(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private String content;
    private Byte isAnonymous;
    private long questionId;
    private long userId;
    private int likeCount;
    private int dislikeCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Byte isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
}
