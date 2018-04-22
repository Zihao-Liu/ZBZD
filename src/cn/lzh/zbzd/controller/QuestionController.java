package cn.lzh.zbzd.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Answer;
import cn.lzh.zbzd.model.Favourite;
import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.Tag;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.model.UserWatchQuestion;
import cn.lzh.zbzd.serviceimpl.AnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.FavouriteServiceImpl;
import cn.lzh.zbzd.serviceimpl.QuestionBelongTagServiceImpl;
import cn.lzh.zbzd.serviceimpl.QuestionServiceImpl;
import cn.lzh.zbzd.serviceimpl.TagServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserWatchQuestionServiceImpl;

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
    UserWatchQuestionServiceImpl userWatchQuestionServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    AnswerServiceImpl answerServiceImpl;
    
    @Autowired
    FavouriteServiceImpl favouriteServiceImpl;

    @Autowired
    User user;

    @Autowired
    User visitor;

    @Autowired
    Question question;

    @Autowired
    Tag tag;
    
    @Autowired
    UserWatchQuestion userWatchQuestion;

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
        String tagName;
        if (tagId == 1) {
            tagName = "无";
        }
        else
            tagName=tagServiceImpl.getTagById(tagId).getName();

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
        user = (User) session.getAttribute("user");
        question.setUserId(user.getId());
        questionServiceImpl.postNewQuestion(question);
        questionBelongTagServiceImpl.insertQuestionBelongTag(question.getId(), tagId);
        request.setAttribute("tagName", tagName);
        request.setAttribute("question", question);
        request.setAttribute("poster", user);
        return "question";
    }

    @RequestMapping(value = "/toQuestion", method = { RequestMethod.GET, RequestMethod.POST })
    public String toQuestion(HttpServletRequest request) {
        Long id = 0L;
        if (request.getAttribute("id") != null)
            id = (Long) request.getAttribute("id");
        else
            id = Long.parseLong(request.getParameter("id"));
        question = questionServiceImpl.getQuestionById(id);
        request.setAttribute("question", question);
        user = userServiceImpl.getUserById(question.getUserId());
        request.setAttribute("poster", user);

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            visitor = (User) session.getAttribute("user");
            request.setAttribute("myAnswer", answerServiceImpl.getAnswerByUserIdAndQuestionId(visitor.getId(), id));
            request.setAttribute("watch", userWatchQuestionServiceImpl.getUserWatchQuestionByUserIdAndQuestionId(visitor.getId(), id));
        }
        
        
        List<Answer> answers = answerServiceImpl.listAnswerByQuestionId(id);
        String act=request.getParameter("act");
        if(act==null||act.equals("time")) 
            answers = answerServiceImpl.listAnswerByQuestionIdOrderByModifiedTime(id);
        else
            answers = answerServiceImpl.listAnswerByQuestionIdOrderByLikeCount(id);
        
        int curPage = 1;
        if (request.getParameter("curPage") != null)
            curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = 5;

        int totalPage = answers.size() / pageSize;
        if (answers.size() % pageSize != 0)
            totalPage += 1;
        int front = (curPage - 1) * pageSize;
        int end = front + pageSize <= answers.size() ? front + pageSize : answers.size();
        answers = answers.subList(front, end);
        request.setAttribute("answers", answers);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("curPage", curPage);
        tag = questionBelongTagServiceImpl.getTagIdByQuestionId(question.getId());
        request.setAttribute("tagName", tag.getName());
        
        List<Favourite> favourites = null;
        if(visitor!=null)
            favourites = favouriteServiceImpl.listFavouriteByUserId(visitor.getId());
        request.setAttribute("favourites", favourites);
        return "question";
    }

    @RequestMapping(value = "/editQuestionAnonymous", method = RequestMethod.GET)
    public String editQuestionAnonymous(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        question = questionServiceImpl.getQuestionById(id);

        HttpSession session = request.getSession();
        if (((User) session.getAttribute("user")).getId() == question.getUserId()) {
            questionServiceImpl.getQuestionById(id);
            if (question.getIsAnonymous() == 1) {
                Byte b = 0;
                question.setIsAnonymous(b);
            } else {
                Byte b = 1;
                question.setIsAnonymous(b);
            }
            question.setModifiedTime(new Date());
            questionServiceImpl.updateQuestion(question);
            request.setAttribute("question", question);
        } else {
            request.setAttribute("error", "您没有权限");
        }
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value="watchQuestion",method=RequestMethod.GET)
    public String watchQuestion(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        question = questionServiceImpl.getQuestionById(id);
        
        user = (User) request.getSession().getAttribute("user");
        
        userWatchQuestion.setCreateTime(new Date());
        userWatchQuestion.setModifiedTime(userWatchQuestion.getCreateTime());
        userWatchQuestion.setUserId(user.getId());
        userWatchQuestion.setQuestionId(id);
        userWatchQuestionServiceImpl.insertUserWatchQuestion(userWatchQuestion);
        request.setAttribute("question", question);
        return "forward:/questionController/toQuestion";
    }
    
    @RequestMapping(value="deleteWatch",method=RequestMethod.GET)
    public String deleteWatch(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        question = questionServiceImpl.getQuestionById(id);
        
        user = (User) request.getSession().getAttribute("user");
        userWatchQuestionServiceImpl.deleteWatchByUserIdAndQuestionId(user.getId(), id);
        request.setAttribute("question", question);
        return "forward:/questionController/toQuestion";
    }

}
