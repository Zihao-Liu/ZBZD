package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class UserResponseAnswer implements Serializable {

    /**
     * create table user_response_answer( 
     * pk_id bigint unsigned auto_increment,
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * is_like tinyint unsigned not null, 
     * user_id bigint unsigned, 
     * answer_id bigint unsigned,
     * primary key(pk_id), 
     * foreign key(user_id) references user(pk_id), 
     * foreign key(answer_id) references answer(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private Byte isLike;
    private long userId;
    private long AnswerId;

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

    public Byte getIsLike() {
        return isLike;
    }

    public void setIsLike(Byte isLike) {
        this.isLike = isLike;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(long answerId) {
        AnswerId = answerId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
