package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.FollowDao;
import cn.lzh.zbzd.model.Follow;
import cn.lzh.zbzd.service.FollowService;

@Service
public class FollowServiceImpl implements FollowService {
    
    @Autowired
    FollowDao followDao;
    
    @Override
    public int insertFollow(Follow follow) {
        return followDao.insertFollow(follow);
    }

    @Override
    public Follow getFollowByFollwerAndFollwing(long followerId, long followingId) {
        return followDao.getFollowByFollwerAndFollwing(followerId, followingId);
    }

    @Override
    public List<Follow> listFollowByFollowerId(long followerId) {
        return followDao.listFollowByFollowerId(followerId);
    }

    @Override
    public List<Follow> listFollowByFollowingId(long followingId) {
        return followDao.listFollowByFollowingId(followingId);
    }

    @Override
    public int deleteFollow(long followerId, long followingId) {
        return followDao.deleteFollow(followerId, followingId);
    }

}
