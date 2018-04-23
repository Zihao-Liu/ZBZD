package cn.lzh.zbzd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.UserCollectAnswer;

public interface UserCollectAnswerDao {
    public int insertUserCollectAnswer(UserCollectAnswer userCollectAnswer);

    public UserCollectAnswer getUserCollectAnswerById(long id);

    public List<UserCollectAnswer> listUserConllectAnswerByUserId(long userId);

    public List<UserCollectAnswer> listUserCollectAnswerByFavouriteId(long favouriteId);

    public int deleteUserCollectAnswer(long id);

    public int deleteUserCollectAnswerByFavouriteId(long favouriteId);

    public int deleteUserCollectAnswerByAnswerId(long answerId);

    public int deleteUserCollectAnswerByAnswerIdAndUserId(@Param(value = "answerId") long answerId,
            @Param(value = "userId") long userId);
}
