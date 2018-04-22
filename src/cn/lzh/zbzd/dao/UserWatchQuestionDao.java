package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.UserWatchQuestion;

public interface UserWatchQuestionDao {
    public int insertUserWatchQuestion(UserWatchQuestion userWatchQuestion);

    public List<Question> listWatchQuestionsByUserId(long userId);

    public UserWatchQuestion getUserWatchQuestionByUserIdAndQuestionId(@Param(value = "userId") long userId,
            @Param(value = "questionId") long questionId);

    public int deleteUserWatchQuestionById(long id);
    
    public int deleteWatchByUserIdAndQuestionId(@Param(value = "userId") long userId,
            @Param(value = "questionId") long questionId);
}
