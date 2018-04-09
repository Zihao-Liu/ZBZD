package cn.lzh.zbzd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.Tag;
import cn.lzh.zbzd.serviceimpl.QuestionBelongTagServiceImpl;
import cn.lzh.zbzd.serviceimpl.TagServiceImpl;

@Controller
@RequestMapping("/tagController")
public class TagController {
    @Autowired
    private TagServiceImpl tagServiceImpl;

    @Autowired
    private QuestionBelongTagServiceImpl questionBelongTagServiceImpl;
    
    @Autowired
    private Tag tag;

    @RequestMapping(value = "/topTag", method = RequestMethod.GET)
    public String topTag(HttpServletRequest request) {
        List<Tag> tags = tagServiceImpl.listAllTopTag();
        request.setAttribute("tags", tags);
        return "tagsquare";
    }

    @RequestMapping(value = "/subTag", method = RequestMethod.GET)
    public String subTag(HttpServletRequest request) {
        int fatherId = Integer.parseInt(request.getParameter("id"));
        List<Tag> subtags = tagServiceImpl.listAllSubTag(fatherId);
        List<Tag> tags = tagServiceImpl.listAllTopTag();
        tag = tagServiceImpl.getTagById(fatherId);
        request.setAttribute("tags", tags);
        request.setAttribute("subtags", subtags);
        request.setAttribute("tag", tag);
        return "tagsquare";
    }

    @RequestMapping(value = "/questionBelongTag", method = RequestMethod.GET)
    public String questionBelongTag(HttpServletRequest request) {
        long tagId = Long.parseLong(request.getParameter("id"));
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Question> questions = questionBelongTagServiceImpl.listQuestionByTagId(tagId);
        int totalPage = questions.size() / pageSize;
        if (questions.size() % pageSize != 0)
            totalPage += 1;
        int front = (curPage - 1) * pageSize;
        int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
        questions = questions.subList(front, end);
        tag = tagServiceImpl.getTagById(tagId);
        request.setAttribute("tag", tag);
        request.setAttribute("curPage", curPage);
        request.setAttribute("questions", questions);
        request.setAttribute("totalPage", totalPage);
        return "tag";
    }
}
