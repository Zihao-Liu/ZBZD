package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.UserWatchQuestion;

public interface UserWatchQuestionService {
    public int insertUserWatchQuestion(UserWatchQuestion userWatchQuestion);

    public List<Question> listWatchQuestionsByUserId(long userId);

    public UserWatchQuestion getUserWatchQuestionByUserIdAndQuestionId(long userId, long questionId);

    public int deleteUserWatchQuestionById(long id);

    public int deleteWatchByUserIdAndQuestionId(long userId, long questionId);
}
