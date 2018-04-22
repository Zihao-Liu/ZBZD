package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.UserWatchQuestionDao;
import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.UserWatchQuestion;
import cn.lzh.zbzd.service.UserWatchQuestionService;

@Service
public class UserWatchQuestionServiceImpl implements UserWatchQuestionService {
    @Autowired
    UserWatchQuestionDao userWatchQuestionDao;

    @Override
    public int insertUserWatchQuestion(UserWatchQuestion userWatchQuestion) {
        return userWatchQuestionDao.insertUserWatchQuestion(userWatchQuestion);
    }

    @Override
    public List<Question> listWatchQuestionsByUserId(long userId) {
        return userWatchQuestionDao.listWatchQuestionsByUserId(userId);
    }

    @Override
    public int deleteUserWatchQuestionById(long id) {
        return userWatchQuestionDao.deleteUserWatchQuestionById(id);
    }

    @Override
    public UserWatchQuestion getUserWatchQuestionByUserIdAndQuestionId(long userId, long questionId) {
        return userWatchQuestionDao.getUserWatchQuestionByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public int deleteWatchByUserIdAndQuestionId(long userId, long questionId) {
        return userWatchQuestionDao.deleteWatchByUserIdAndQuestionId(userId, questionId);
    }

}
