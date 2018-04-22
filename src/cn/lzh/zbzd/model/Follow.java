package cn.lzh.zbzd.model;

import java.io.Serializable;
import java.util.Date;

public class Follow implements Serializable {

    /**
     * pk_id bigint unsigned auto_increment, gmt_create datetime not null,
     * gmt_modified datetime not null, follower_id bigint unsigned, following_id
     * bigint unsigned, primary key(pk_id), foreign key(follower_id) references
     * user(pk_id), foreign key(following_id) references user(pk_id)
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private Date createTime;
    private Date modifiedTime;
    private long followerId;
    private long followingId;
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

    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    public long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(long followingId) {
        this.followingId = followingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
