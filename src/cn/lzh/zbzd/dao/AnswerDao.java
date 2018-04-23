package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.Answer;

public interface AnswerDao {
    public Answer getAnswerById(long id);

    public List<Answer> listAnswerByUserId(long userId);

    public List<Answer> listAnswerByQuestionId(long questionId);

    public List<Answer> listAnswerByQuestionIdOrderByModifiedTime(long questionId);

    public List<Answer> listAnswerByQuestionIdOrderByLikeCount(long questionId);

    public List<Answer> listAnswerByFavouriteId(long favouriteId);

    public int insertAnswer(Answer answer);

    public Answer getAnswerByUserIdAndQuestionId(@Param(value = "userId") long userId,
            @Param(value = "questionId") long questionId);

    public int updateAnswer(Answer answer);

    public int deleteAnswer(long id);
}
