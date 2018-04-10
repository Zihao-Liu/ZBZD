package cn.lzh.zbzd.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.QuestionDao;
import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionDao questionDao;

    @Override
    public int postNewQuestion(Question question) {
        return questionDao.insertQuestion(question);
    }

    @Override
    public int deleteQuestion(long id) {
        return questionDao.deleteQuestionById(id);
    }

    @Override
    public List<Question> listAllQuestionOrderByModifiedTime() {
        return questionDao.listAllQuestionOrderByModifiedTime();
    }

    @Override
    public List<Question> listAllQuestionByUserId(long userId) {
        return questionDao.listQuestionByUserId(userId);
    }

    @Override
    public Question getQuestionById(long id) {
        return questionDao.getQuestionById(id);
    }

    @Override
    public int updateQuestion(Question question) {
        return questionDao.updateQuestion(question);
    }

    @Override
    public List<Question> listAllQuestionOrderByAnswerCount() {
        return questionDao.listAllQuestionOrderByAnswerCount();
    }

}
