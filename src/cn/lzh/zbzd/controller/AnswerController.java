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
import cn.lzh.zbzd.model.UserCollectAnswer;
import cn.lzh.zbzd.model.UserResponseAnswer;
import cn.lzh.zbzd.serviceimpl.AnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserCollectAnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserResponseAnswerServiceImpl;

@Controller
@RequestMapping("/answerController")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerServiceImpl;
    
    @Autowired
    private UserResponseAnswerServiceImpl userResponseAnswerServiceImpl;
    
    @Autowired
    private UserCollectAnswerServiceImpl userCollectAnswerServiceImpl;
    
    @Autowired
    private User user;

    @Autowired
    private Answer answer;
    
    @Autowired
    private UserResponseAnswer userResponseAnswer;
    
    @Autowired
    private UserCollectAnswer userCollectAnswer;

    @RequestMapping(value = "/postAnswer", method = RequestMethod.POST)
    public String postAnswer(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("gb2312");
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        long questionId = Long.parseLong(request.getParameter("questionId"));
        String content = request.getParameter("answercontent");
        if (content == "" || content.equals(null)) {
            request.setAttribute("error", "内容不能为空");
        } else {
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

    @RequestMapping(value = "/editAnswerAnonymous", method = RequestMethod.GET)
    public String editAnswerAnonymous(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        answer = answerServiceImpl.getAnswerById(id);

        HttpSession session = request.getSession();
        if (((User) session.getAttribute("user")).getId() == answer.getUserId()) {
            answerServiceImpl.getAnswerById(id);
            if (answer.getIsAnonymous() == 1) {
                Byte b = 0;
                answer.setIsAnonymous(b);
            } else {
                Byte b = 1;
                answer.setIsAnonymous(b);
            }
            answer.setModifiedTime(new Date());
            answerServiceImpl.updateAnswer(answer);
            request.setAttribute("id", answer.getQuestionId());
        } else {
            request.setAttribute("error", "您没有权限");
        }
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value = "/responseAnswer", method = RequestMethod.GET)
    public String responseAnswer(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        if(user==null)
            return "signin";
        answer = answerServiceImpl.getAnswerById(id);
        if(userResponseAnswerServiceImpl.getResponseByUserIdAndAnswerId(user.getId(), id)!=null) {
            request.setAttribute("error", "您已经评价过");
        }else {
            userResponseAnswer.setCreateTime(new Date());
            userResponseAnswer.setModifiedTime(userResponseAnswer.getCreateTime());
            userResponseAnswer.setAnswerId(id);
            userResponseAnswer.setUserId(user.getId());
            Byte like = 1;
            String act = request.getParameter("act");
            if(act==null||act.equals("1")) {
                userResponseAnswer.setIsLike(like);
            }else {
                like=0;
                userResponseAnswer.setIsLike(like);
            }

            userResponseAnswerServiceImpl.insertResponse(userResponseAnswer);
        }
        request.setAttribute("id", answer.getQuestionId());
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value = "/deleteResponse", method = RequestMethod.GET)
    public String deleteResponse(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        if(user==null)
            return "signin";
        answer = answerServiceImpl.getAnswerById(id);
        userResponseAnswer=userResponseAnswerServiceImpl.getResponseByUserIdAndAnswerId(user.getId(), id);
        if(userResponseAnswer==null) {
            request.setAttribute("error", "您没有评价过");
        }else {
            userResponseAnswerServiceImpl.deleteResponseById(userResponseAnswer.getId());
        }
        
        request.setAttribute("id", answer.getQuestionId());
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value="/collectAnswer",method=RequestMethod.POST)
    public String collectAnswer(HttpServletRequest request) {
        long answerId = Long.parseLong(request.getParameter("id"));
        long favouriteId=Long.parseLong(request.getParameter("favouriteId"));
        long questionId = Long.parseLong(request.getParameter("questionId"));
        HttpSession session = request.getSession();
        user=(User) session.getAttribute("user");
        if(user==null) {
            request.setAttribute("error", "请登录");
            return "signin";
        }
        
        userCollectAnswer.setAnswerId(answerId);
        userCollectAnswer.setFavouriteId(favouriteId);
        userCollectAnswer.setCreateTime(new Date());
        userCollectAnswer.setModifiedTime(userCollectAnswer.getCreateTime());
        userCollectAnswer.setUserId(user.getId());
        
        userCollectAnswerServiceImpl.insertUserCollectAnswer(userCollectAnswer);
        request.setAttribute("id", questionId);
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value="/deleteCollect",method=RequestMethod.GET)
    public String deleteCollect(HttpServletRequest request) {
        long answerId = Long.parseLong(request.getParameter("id"));
        long questionId = Long.parseLong(request.getParameter("qid"));
        HttpSession session = request.getSession();
        user=(User) session.getAttribute("user");
        if(user==null) {
            request.setAttribute("error", "请登录");
            return "signin";
        }
        userCollectAnswerServiceImpl.deleteUserCollectAnswerByAnswerIdAndUserId(answerId, user.getId());
        request.setAttribute("id", questionId);
        return "forward:/questionController/toQuestion";
    }
    
}
