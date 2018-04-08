package cn.lzh.zbzd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.serviceimpl.QuestionServiceImpl;

@Controller
public class WelcomeController {
    
    @Autowired
    QuestionServiceImpl questionServiceImpl;
    

    @RequestMapping(value="/")
    public String toIndex(HttpServletRequest request) {
        List<Question> questions = questionServiceImpl.listAllQuestionOrderByModifiedTime();
        System.out.println(questions.size());
        int curPage = 1;
        int pageSize = 10;
        int totalPage = questions.size()/pageSize;
        System.out.println("total:"+totalPage);
        if(questions.size()%pageSize!=0)
            totalPage+=1;
        System.out.println("total:"+totalPage);
        int front = (curPage-1)*pageSize;
        int end = front+pageSize<=questions.size()?front+pageSize:questions.size();
        questions=questions.subList(front,end);
        request.setAttribute("curPage", curPage);
        request.setAttribute("questions", questions);
        request.setAttribute("totalPage", totalPage);
        return "index";
    }
    
}
