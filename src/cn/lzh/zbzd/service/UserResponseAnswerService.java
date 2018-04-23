package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.UserResponseAnswer;

public interface UserResponseAnswerService {
    public int insertResponse(UserResponseAnswer userResponseAnswer);

    public UserResponseAnswer getResponseById(long id);

    public List<UserResponseAnswer> listResponseByUserId(long userId);

    public List<UserResponseAnswer> listResponseByAnswerId(long answerId);

    public int getLikeResponseCountByUserId(long userId);

    public int getDislikeResponseCountByUserId(long userId);

    public UserResponseAnswer getResponseByUserIdAndAnswerId(long userId, long answerId);

    public int deleteResponseById(long id);

    public int deleteResponseByAnswerId(long answerId);
}
