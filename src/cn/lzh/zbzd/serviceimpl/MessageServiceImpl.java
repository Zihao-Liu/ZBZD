package cn.lzh.zbzd.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.MessageDao;
import cn.lzh.zbzd.model.Message;
import cn.lzh.zbzd.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public int insertMessage(Message message) {
        return messageDao.insertMessage(message);
    }

    @Override
    public int deleteMessageById(long id) {
        return messageDao.deleteMessageById(id);
    }

    @Override
    public List<Message> getMessageBySenderId(long senderId) {
        return messageDao.getMessageBySenderId(senderId);
    }

    @Override
    public List<Message> getMessageByReceiverId(long receiverId) {
        return messageDao.getMessageByReceiverId(receiverId);
    }

    @Override
    public List<Message> getMessageByReceiverIdAndTime(long receiverId, Date lastSignInTime) {
        return messageDao.getMessageByReceiverIdAndTime(receiverId, lastSignInTime);
    }

    @Override
    public int updateIsRead(long id) {
        return messageDao.updateIsRead(id);
    }

}
