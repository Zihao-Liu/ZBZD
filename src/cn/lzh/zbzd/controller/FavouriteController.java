package cn.lzh.zbzd.controller;

import java.io.UnsupportedEncodingException;
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
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.AnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.FavouriteServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserCollectAnswerServiceImpl;

@Controller
@RequestMapping("/favouriteController")
public class FavouriteController {
    @Autowired
    FavouriteServiceImpl favouriteServiceImpl;

    @Autowired
    AnswerServiceImpl answerServiceImpl;

    @Autowired
    UserCollectAnswerServiceImpl userCollectAnswerServiceImpl;

    @Autowired
    Favourite favourite;

    @Autowired
    User user;

    @Autowired
    Answer answer;

    @RequestMapping(value = "/toInsert", method = RequestMethod.GET)
    public String toInsert(HttpServletRequest request) {
        return "insertfavourite";
    }

    @RequestMapping(value = "/insertFavourite", method = RequestMethod.POST)
    public String insertFavourite(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("gb2312");
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "ÄúÃ»ÓÐµÇÂ¼");
            return "signin";
        }

        String name = request.getParameter("name");
        favourite.setCreateTime(new Date());
        favourite.setModifiedTime(favourite.getCreateTime());
        favourite.setName(name);
        favourite.setUserId(user.getId());

        favouriteServiceImpl.insertFavourite(favourite);
        return "forward:/userController/personal";
    }

    @RequestMapping(value = "/toFavourite", method = RequestMethod.GET)
    public String toFavourite(HttpServletRequest request) {
        long favouriteId = Long.parseLong(request.getParameter("id"));
        List<Answer> answers = answerServiceImpl.listAnswerByFavouriteId(favouriteId);
        request.setAttribute("answers", answers);
        favourite = favouriteServiceImpl.getFavouriteById(favouriteId);
        request.setAttribute("favourite", favourite);

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
        return "favourite";
    }

    @RequestMapping(value = "/deleteFavourite", method = RequestMethod.GET)
    public String deleteFavourite(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        userCollectAnswerServiceImpl.deleteUserCollectAnswerByFavouriteId(id);
        favouriteServiceImpl.deleteFavourite(id);
        return "forward:/userController/personal";
    }
}
