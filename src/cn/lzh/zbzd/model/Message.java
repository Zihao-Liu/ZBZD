package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    /**
     * create table message( 
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime not null, 
     * gmt_modified datetime not null, 
     * sender_id bigint unsigned not null, 
     * receiver_id bigint unsigned not null, 
     * content text not null,
     * is_read tinyint unsigned default 0,
     * primary key(pk_id), 
     * foreign key(sender_id) references user(pk_id), 
     * foreign  key(receiver_id) references user(pk_id) ); 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private long senderId;
    private long receiverId;
    private String content;
    private Byte isRead;
    private String name;

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

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
