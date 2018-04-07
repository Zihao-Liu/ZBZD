package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {

    /**
     * create table question( 
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * title varchar(100) not null, 
     * content text not null, 
     * is_anonymous tinyint unsigned default 0, 
     * user_id bigint unsigned not null, 
     * primary key(pk_id), 
     * foreign key(user_id) references user(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private String title;
    private String content;
    private Byte isAnonymous;
    private long userId;

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

    public Byte getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Byte isAnonymous) {
        this.isAnonymous = isAnonymous;
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

}
