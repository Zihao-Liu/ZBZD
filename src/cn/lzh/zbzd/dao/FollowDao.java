package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.Follow;

public interface FollowDao {
    public int insertFollow(Follow follow);

    public Follow getFollowByFollwerAndFollwing(@Param(value = "followerId") long followerId,
            @Param(value = "followingId") long followingId);

    public List<Follow> listFollowByFollowerId(long followerId);

    public List<Follow> listFollowByFollowingId(long followingId);

    public int deleteFollow(@Param(value = "followerId") long followerId,
            @Param(value = "followingId") long followingId);
}
