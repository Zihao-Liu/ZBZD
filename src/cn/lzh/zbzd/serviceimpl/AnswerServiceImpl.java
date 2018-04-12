package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.AnswerDao;
import cn.lzh.zbzd.model.Answer;
import cn.lzh.zbzd.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public Answer getAnswerById(long id) {
        return answerDao.getAnswerById(id);
    }

    @Override
    public List<Answer> listAnswerByUserId(long userId) {
        return answerDao.listAnswerByUserId(userId);
    }

    @Override
    public List<Answer> listAnswerByQuestionId(long questionId) {
        return answerDao.listAnswerByQuestionId(questionId);
    }

    @Override
    public int insertAnswer(Answer answer) {
        return answerDao.insertAnswer(answer);
    }

    @Override
    public Answer getAnswerByUserIdAndQuestionId(long userId, long questionId) {
        return answerDao.getAnswerByUserIdAndQuestionId(userId, questionId);
    }

    @Override
    public int updateAnswer(Answer answer) {
        return answerDao.updateAnswer(answer);
    }

    @Override
    public List<Answer> listAnswerByQuestionIdOrderByModifiedTime(long questionId) {
        return answerDao.listAnswerByQuestionIdOrderByModifiedTime(questionId);
    }

    @Override
    public List<Answer> listAnswerByQuestionIdOrderByLikeCount(long questionId) {
        return answerDao.listAnswerByQuestionIdOrderByLikeCount(questionId);
    }

    @Override
    public List<Answer> listAnswerByFavouriteId(long favouriteId) {
        return answerDao.listAnswerByFavouriteId(favouriteId);
    }

}
