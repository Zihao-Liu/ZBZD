package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Question;

public interface QuestionService {
    public int postNewQuestion(Question question);

    public int deleteQuestion(long id);

    public List<Question> listAllQuestionOrderByModifiedTime();

    public List<Question> listAllQuestionByUserId(long userId);
    
    public Question getQuestionById(long id);
    
    public int updateQuestion(Question question);
    
}
