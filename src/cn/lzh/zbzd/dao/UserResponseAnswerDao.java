package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.UserResponseAnswer;

public interface UserResponseAnswerDao {
    public int insertResponse(UserResponseAnswer userResponseAnswer);

    public UserResponseAnswer getResponseById(long id);

    public Integer getLikeResponseCountByUserId(long userId);

    public Integer getDislikeResponseCountByUserId(long userId);

    public List<UserResponseAnswer> listResponseByUserId(long userId);

    public List<UserResponseAnswer> listResponseByAnswerId(long answerId);

    public UserResponseAnswer getResponseByUserIdAndAnswerId(@Param(value = "userId") long userId,
            @Param(value = "answerId") long answerId);

    public int deleteResponseById(long id);
    
    public int deleteResponseByAnswerId(long answerId);
}
