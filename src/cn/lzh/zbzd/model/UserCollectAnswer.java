package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class UserCollectAnswer implements Serializable {
    
    /**
     * create table user_collect_answer( 
     * pk_id bigint unsigned auto_increment,
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * user_id bigint unsigned, 
     * answer_id bigint unsigned, 
     * favourite_id bigint unsigned, 
     * primary key(pk_id), 
     * foreign key(user_id) references user(pk_id), 
     * foreign key(answer_id) references answer(pk_id), 
     * foreign key(favourite_id) references favourite(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private long userId;
    private long answerId;
    private long favouriteId;

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

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(long favouriteId) {
        this.favouriteId = favouriteId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
