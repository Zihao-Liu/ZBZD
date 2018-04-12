package cn.lzh.zbzd.service;

import java.util.List;

import cn.lzh.zbzd.model.UserCollectAnswer;

public interface UserCollectAnswerService {
    public int insertUserCollectAnswer(UserCollectAnswer userCollectAnswer);
    public UserCollectAnswer getUserCollectAnswerById(long id);
    public List<UserCollectAnswer> listUserConllectAnswerByUserId(long userId);
    public List<UserCollectAnswer> listUserCollectAnswerByFavouriteId(long favouriteId);
    public int deleteUserCollectAnswer(long id);
    public int deleteUserCollectAnswerByFavouriteId(long favouriteId);
    public int deleteUserCollectAnswerByAnswerIdAndUserId(long answerId, long userId);
}
