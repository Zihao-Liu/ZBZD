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
    public List<Answer> listAnswerByQuestionId(long userId) {
        return answerDao.listAnswerByQuestionId(userId);
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

}
