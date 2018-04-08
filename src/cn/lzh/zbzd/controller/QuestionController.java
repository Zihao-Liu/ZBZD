package cn.lzh.zbzd.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.QuestionBelongTagServiceImpl;
import cn.lzh.zbzd.serviceimpl.QuestionServiceImpl;
import cn.lzh.zbzd.serviceimpl.TagServiceImpl;

@Controller
@RequestMapping("/questionController")
public class QuestionController {
    @Autowired
    TagServiceImpl tagServiceImpl;

    @Autowired
    QuestionServiceImpl questionServiceImpl;
    
    @Autowired
    QuestionBelongTagServiceImpl questionBelongTagServiceImpl;
    
    @Autowired
    User user;
    
    @Autowired
    Question question;

    @RequestMapping(value = "/toPostQuestion", method = RequestMethod.GET)
    public String toPostQuestion(HttpServletRequest request) {
        request.setAttribute("tags", tagServiceImpl.listAllTag());
        return "postquestion";
    }

    @RequestMapping(value = "/postQuestion", method = RequestMethod.POST)
    public String postQuestion(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("gb2312");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (title == "" || content == "" || title.equals(null) || content.equals(null)) {
            request.setAttribute("error", "标题与内容不能为空");
            return "postquestion";
        }
        long tagId = Long.parseLong(request.getParameter("tag"));
        String tagName = tagServiceImpl.getTagById(tagId).getName();
        if(tagId==0)
            tagName="无";
        
        Byte isAnonymous;
        if (request.getParameter("isAnonymous") != null)
            isAnonymous = 1;
        else
            isAnonymous = 0;

        question.setCreateTime(new Date());
        question.setModifiedTime(question.getCreateTime());
        question.setTitle(title);
        question.setContent(content);
        question.setIsAnonymous(isAnonymous);
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        System.out.println(user.getId());
        question.setUserId(user.getId());
        questionServiceImpl.postNewQuestion(question);
        questionBelongTagServiceImpl.insertQuestionBelongTag(question.getId(), tagId);
        request.setAttribute("tagName", tagName);
        request.setAttribute("question", question);
        request.setAttribute("poster", user);
        return "question";
    }
}
