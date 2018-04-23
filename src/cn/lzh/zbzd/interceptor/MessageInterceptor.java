package cn.lzh.zbzd.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.lzh.zbzd.model.Message;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.MessageServiceImpl;

public class MessageInterceptor implements HandlerInterceptor {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private User user;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
            ModelAndView modelAndView) throws Exception {
        user = (User) request.getSession().getAttribute("user");

        if (null != user) {
            HttpSession session = request.getSession();
            List<Message> messages = messageServiceImpl.getMessageByReceiverIdAndTime(user.getId(),
                    user.getSignInTime());
            session.setAttribute("messages", messages);
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e)
            throws Exception {

    }
}
