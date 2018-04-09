package cn.lzh.zbzd.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Answer;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.AnswerServiceImpl;

@Controller
@RequestMapping("/answerController")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerServiceImpl;
    
    @Autowired
    private User user;
    
    @Autowired
    private Answer answer;
    
    @RequestMapping(value="/postAnswer",method=RequestMethod.POST)
    public String postAnswer(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("gb2312");
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        long questionId = Long.parseLong(request.getParameter("questionId"));
        String content = request.getParameter("answercontent");
        if(content==""||content.equals(null)) {
            request.setAttribute("error", "���ݲ���Ϊ��");
        }else {
            Byte isAnonymous;
            if (request.getParameter("isAnonymous") != null)
                isAnonymous = 1;
            else
                isAnonymous = 0;
            
            answer.setCreateTime(new Date());
            answer.setModifiedTime(answer.getCreateTime());
            answer.setContent(content);
            answer.setIsAnonymous(isAnonymous);
            answer.setQuestionId(questionId);
            answer.setUserId(user.getId());
            answerServiceImpl.insertAnswer(answer);
        }
       
        request.setAttribute("curPage", 1);
        request.setAttribute("id", questionId);
        return "forward:/questionController/toQuestion";
    }
}