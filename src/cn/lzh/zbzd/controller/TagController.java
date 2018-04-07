package cn.lzh.zbzd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Tag;
import cn.lzh.zbzd.serviceimpl.TagServiceImpl;

@Controller
@RequestMapping("/tagController")
public class TagController {
    @Autowired
    private TagServiceImpl tagServiceImpl;
    
    @Autowired
    private Tag tag;
    
    @RequestMapping(value="/topTag",method=RequestMethod.GET)
    public String topTag(HttpServletRequest request) {
        List<Tag> tags= tagServiceImpl.listAllTopTag();
        request.setAttribute("tags", tags);
        return "tagsquare";
    }
    
    @RequestMapping(value="/subTag",method=RequestMethod.GET)
    public String subTag(HttpServletRequest request) {
        int fatherID = Integer.parseInt(request.getParameter("id"));
        List<Tag> subtags = tagServiceImpl.listAllSubTag(fatherID);
        List<Tag> tags= tagServiceImpl.listAllTopTag();
        request.setAttribute("tags", tags);
        request.setAttribute("subtags", subtags);
        return "tagsquare";
    }
}
