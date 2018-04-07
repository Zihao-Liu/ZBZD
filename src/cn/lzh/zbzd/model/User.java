package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * create table user( 
     * pk_id bigint unsigned auto_increment, 
     * gmt_create datetime
     * not null, gmt_modified datetime not null, 
     * uk_username varchar(20) not null,
     * password varchar(20) not null, 
     * nickname varchar(20) not null, 
     * privacy tinyint unsigned default 0, 
     * gender varchar(6) default 'male', 
     * introduction text,
     * primary key(pk_id));
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private String username;
    private String password;
    private String nickname;
    private int privacy;
    private String gender;
    private String introduction;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
