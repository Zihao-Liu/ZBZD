package cn.lzh.zbzd.dao;

import java.util.List;

import cn.lzh.zbzd.model.Question;

public interface QuestionDao {
    public Question getQuestionById(long id);

    public List<Question> listQuestionByUserId(long userId);

    public int deleteQuestionById(long id);

    public int insertQuestion(Question question);

    public List<Question> listAllQuestionOrderByModifiedTime();

    public List<Question> updateQuestion(Question question);
}
