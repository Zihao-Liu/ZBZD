package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.UserResponseAnswerDao;
import cn.lzh.zbzd.model.UserResponseAnswer;
import cn.lzh.zbzd.service.UserResponseAnswerService;

@Service
public class UserResponseAnswerServiceImpl implements UserResponseAnswerService{
    @Autowired
    UserResponseAnswerDao userResponseAnswerDao;
    
    @Override
    public int insertResponse(UserResponseAnswer userResponseAnswer) {
        return userResponseAnswerDao.insertResponse(userResponseAnswer);
    }

    @Override
    public UserResponseAnswer getResponseById(long id) {
        return userResponseAnswerDao.getResponseById(id);
    }

    @Override
    public List<UserResponseAnswer> listResponseByUserId(long userId) {
        return userResponseAnswerDao.listResponseByUserId(userId);
    }

    @Override
    public List<UserResponseAnswer> listResponseByAnswerId(long answerId) {
        return userResponseAnswerDao.listResponseByAnswerId(answerId);
    }

    @Override
    public UserResponseAnswer getResponseByUserIdAndAnswerId(long userId, long answerId) {
        return userResponseAnswerDao.getResponseByUserIdAndAnswerId(userId, answerId);
    }

    @Override
    public int deleteResponseById(long id) {
        return userResponseAnswerDao.deleteResponseById(id);
    }

    @Override
    public int getLikeResponseCountByUserId(long userId) {
        Integer count = userResponseAnswerDao.getLikeResponseCountByUserId(userId);
        return count!=null?count:0;
    }

    @Override
    public int getDislikeResponseCountByUserId(long userId) {
        Integer count = userResponseAnswerDao.getDislikeResponseCountByUserId(userId);
        return count!=null?count:0;
    }

    @Override
    public int deleteResponseByAnswerId(long answerId) {
        return userResponseAnswerDao.deleteResponseByAnswerId(answerId);
    }

}
