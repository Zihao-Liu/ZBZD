package cn.lzh.zbzd.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lzh.zbzd.model.Message;

public interface MessageDao {
    public int insertMessage(Message message);

    public int deleteMessageById(long id);

    public List<Message> getMessageBySenderId(long senderId);

    public List<Message> getMessageByReceiverId(long receiverId);

    public List<Message> getMessageByReceiverIdAndTime(@Param(value = "receiverId") long receiverId,
            @Param(value = "lastSignInTime") Date lastSignInTime);

    public int updateIsRead(long id);

}
