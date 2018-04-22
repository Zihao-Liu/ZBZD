package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class UserWatchQuestion implements Serializable {

    /**
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime not null,
     * gmt_modified datetime not null, 
     * user_id bigint unsigned, 
     * question_id bigint unsigned, 
     * primary key (pk_id), 
     * foreign key(user_id) references user(pk_id),
     * foreign key(question_id) references question(pk_id)
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private long userId;
    private long questionId;
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
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
