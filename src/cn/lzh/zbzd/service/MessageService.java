package cn.lzh.zbzd.service;

import java.util.Date;
import java.util.List;

import cn.lzh.zbzd.model.Message;

public interface MessageService {
    public int insertMessage(Message message);

    public int deleteMessageById(long id);

    public List<Message> getMessageBySenderId(long senderId);

    public List<Message> getMessageByReceiverId(long receiverId);

    public List<Message> getMessageByReceiverIdAndTime(long receiverId, Date lastSignInTime);

    public int updateIsRead(long id);
}
