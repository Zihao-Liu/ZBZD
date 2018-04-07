package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {
    /**
     * create table tag( 
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * name varchar(10) unique, 
     * father bigint unsigned default 0, 
     * primary key(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private String name;
    private long fatherId;

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

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }
}
