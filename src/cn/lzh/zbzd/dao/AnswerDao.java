package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.Answer;

public interface AnswerDao {
    public Answer getAnswerById(long id);
    public List<Answer> listAnswerByUserId(long userId);
    public List<Answer> listAnswerByQuestionId(long userId);
    public int insertAnswer(Answer answer);
    public Answer getAnswerByUserIdAndQuestionId(@Param(value="userId")long userId, @Param(value="questionId")long questionId);
}
