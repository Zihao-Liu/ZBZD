package cn.lzh.zbzd.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Message;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.MessageServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserServiceImpl;

@Controller
@RequestMapping("/messageController")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private User user;

    @Autowired
    private Message message;

    @RequestMapping(value = "/toSendMessage", method = RequestMethod.GET)
    public String toSendMessage(HttpServletRequest request) {
        long receiverId = Long.parseLong(request.getParameter("id"));
        user = userServiceImpl.getUserById(receiverId);
        request.setAttribute("receiver", user);
        return "sendmessage";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("gb2312");
        long receiverId = Long.parseLong(request.getParameter("id"));
        String content = request.getParameter("content");
        user = (User) request.getSession().getAttribute("user");
        message.setCreateTime(new Date());
        message.setModifiedTime(message.getCreateTime());
        message.setContent(content);
        message.setSenderId(user.getId());
        message.setReceiverId(receiverId);
        messageServiceImpl.insertMessage(message);
        request.setAttribute("id", receiverId);
        return "forward:/userController/userPage";
    }

    @RequestMapping(value = "/toMessage", method = RequestMethod.GET)
    public String toMessage(HttpServletRequest request) {
        user = (User) request.getSession().getAttribute("user");

        List<Message> messages;
        String act = request.getParameter("act");
        if (act == null || act.equals("recv")) {
            messages = messageServiceImpl.getMessageByReceiverId(user.getId());
            request.setAttribute("messages", messages);
            for (Message message : messages) {
                messageServiceImpl.updateIsRead(message.getId());
            }
        } else {
            messages = messageServiceImpl.getMessageBySenderId(user.getId());
            request.setAttribute("messages", messages);
        }

        int curPage = 1;
        int pageSize = 10;
        int totalPage = messages.size() / pageSize;
        if (messages.size() % pageSize != 0)
            totalPage += 1;
        int front = (curPage - 1) * pageSize;
        int end = front + pageSize <= messages.size() ? front + pageSize : messages.size();
        messages = messages.subList(front, end);
        request.setAttribute("curPage", curPage);
        request.setAttribute("totalPage", totalPage);

        return "messagelist";
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
    public String deleteMessage(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        messageServiceImpl.deleteMessageById(id);
        return "forward:/messageController/toMessage";
    }

}
