package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Follow;

public interface FollowService {
    public int insertFollow(Follow follow);

    public Follow getFollowByFollwerAndFollwing(long followerId, long followingId);

    public List<Follow> listFollowByFollowerId(long followerId);

    public List<Follow> listFollowByFollowingId(long followingId);

    public int deleteFollow(long followerId, long followingId);
}
