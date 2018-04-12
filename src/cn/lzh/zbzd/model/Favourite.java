package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Favourite implements Serializable {

    /**
     * create table favourite(
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * name varchar(10) not null,
     * user_id bigint unsigned, 
     * primary key (pk_id), 
     * foreign key(user_id) references user(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
