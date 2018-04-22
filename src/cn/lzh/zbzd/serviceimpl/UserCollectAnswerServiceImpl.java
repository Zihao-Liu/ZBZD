package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.UserCollectAnswerDao;
import cn.lzh.zbzd.model.UserCollectAnswer;
import cn.lzh.zbzd.service.UserCollectAnswerService;

@Service
public class UserCollectAnswerServiceImpl implements UserCollectAnswerService{
    @Autowired
    UserCollectAnswerDao userCollectAnswerDao;
    
    @Override
    public int insertUserCollectAnswer(UserCollectAnswer userCollectAnswer) {
        return userCollectAnswerDao.insertUserCollectAnswer(userCollectAnswer);
    }

    @Override
    public UserCollectAnswer getUserCollectAnswerById(long id) {
        return userCollectAnswerDao.getUserCollectAnswerById(id);
    }

    @Override
    public List<UserCollectAnswer> listUserConllectAnswerByUserId(long userId) {
        return userCollectAnswerDao.listUserConllectAnswerByUserId(userId);
    }

    @Override
    public List<UserCollectAnswer> listUserCollectAnswerByFavouriteId(long favouriteId) {
        return userCollectAnswerDao.listUserCollectAnswerByFavouriteId(favouriteId);
    }

    @Override
    public int deleteUserCollectAnswer(long id) {
        return userCollectAnswerDao.deleteUserCollectAnswer(id);
    }

    @Override
    public int deleteUserCollectAnswerByAnswerIdAndUserId(long answerId, long userId) {
        return userCollectAnswerDao.deleteUserCollectAnswerByAnswerIdAndUserId(answerId, userId);
    }

    @Override
    public int deleteUserCollectAnswerByFavouriteId(long favouriteId) {
        return userCollectAnswerDao.deleteUserCollectAnswerByFavouriteId(favouriteId);
    }

    @Override
    public int deleteUserCollectAnswerByAnswerId(long answerId) {
        return userCollectAnswerDao.deleteUserCollectAnswerByAnswerId(answerId);
    }

}
