package cn.lzh.zbzd.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.lzh.zbzd.model.Answer;
import cn.lzh.zbzd.model.Favourite;
import cn.lzh.zbzd.model.Follow;
import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.AnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.FavouriteServiceImpl;
import cn.lzh.zbzd.serviceimpl.FollowServiceImpl;
import cn.lzh.zbzd.serviceimpl.QuestionServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserResponseAnswerServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserWatchQuestionServiceImpl;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private User user;

    @Autowired
    private Follow follow;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private AnswerServiceImpl answerServiceImpl;

    @Autowired
    private UserResponseAnswerServiceImpl userResponseAnswerServiceImpl;

    @Autowired
    private FavouriteServiceImpl favouriteServiceImpl;

    @Autowired
    private UserWatchQuestionServiceImpl userWatchQuestionServiceImpl;

    @Autowired
    private FollowServiceImpl followServiceImpl;

    @RequestMapping(value = "/toSignUp", method = RequestMethod.GET)
    public String toSignUp(HttpServletRequest request) {
        return "signup";
    }

    @RequestMapping(value = "/toSignIn", method = RequestMethod.GET)
    public String toSignIn(HttpServletRequest request) {
        return "signin";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String pattern = "^[A-Za-z0-9]{0,40}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m;
        if (username.equals(null) || username == "") {
            request.setAttribute("error", "用户名不能为空");
            return "signup";
        } else {
            m = r.matcher(username);
            if (!m.matches()) {
                request.setAttribute("error", "用户名只能为数字和字母");
                return "signup";
            }
        }

        if (password.equals(null) || password == "") {
            request.setAttribute("error", "密码不能为空");
            return "signup";
        } else {
            m = r.matcher(password);
            if (!m.matches()) {
                request.setAttribute("error", "密码只能为数字和字母");
                return "signup";
            }
        }

        if (nickname.equals(null) || nickname == "") {
            request.setAttribute("error", "昵称不能为空");
            return "signup";
        } else {
            m = r.matcher(nickname);
            if (!m.matches()) {
                request.setAttribute("error", "昵称只能为数字和字母");
                return "signup";
            }
        }
        if (userServiceImpl.checkExistUsername(request.getParameter("username")) == null) {
            // User user = new User();
            user.setCreateTime(new Date());
            user.setModifiedTime(user.getCreateTime());
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setGender(request.getParameter("gender"));
            user.setNickname(request.getParameter("nickname"));
            user.setIntroduction(request.getParameter("introduction"));
            user.setPrivacy(Integer.parseInt(request.getParameter("privacy")));
            userServiceImpl.signUp(user);
        } else {
            request.setAttribute("error", "用户名已存在");
            return "signup";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "forward:/";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pattern = "^[A-Za-z0-9]{4,40}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m;
        if (username.equals(null) || username == "") {
            request.setAttribute("error", "用户名不能为空");
            return "signin";
        } else {
            m = r.matcher(username);
            if (!m.matches()) {
                request.setAttribute("error", "用户名只能为数字和字母");
                return "signin";
            }
        }

        if (password.equals(null) || password == "") {
            request.setAttribute("error", "密码不能为空");
            return "signin";
        } else {
            m = r.matcher(password);
            if (!m.matches()) {
                request.setAttribute("error", "密码只能为数字和字母");
                return "signin";
            }
        }

        User user = userServiceImpl.signIn(username, password);
        if (user == null) {
            request.setAttribute("error", "用户名或密码错误");
            return "signin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "forward:/";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "forward:/";
    }

    @RequestMapping(value = "/personal", method = { RequestMethod.GET, RequestMethod.POST })
    public String personal(HttpServletRequest request) {
        HttpSession session;
        session = request.getSession();
        user = (User) session.getAttribute("user");

        int curPage = 1;
        if (request.getParameter("curPage") != null)
            curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = 5;

        String act = request.getParameter("act");
        if (act == null || act.equals("que")) {
            List<Question> questions = questionServiceImpl.listAllQuestionByUserId(user.getId());
            int totalPage = questions.size() / pageSize;
            if (questions.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
            questions = questions.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("questions", questions);
            request.setAttribute("totalPage", totalPage);
        } else if (act.equals("ans")) {
            List<Answer> answers = answerServiceImpl.listAnswerByUserId(user.getId());
            int totalPage = answers.size() / pageSize;
            if (answers.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= answers.size() ? front + pageSize : answers.size();
            answers = answers.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("answers", answers);
            request.setAttribute("totalPage", totalPage);
        } else {
            List<Question> questions = userWatchQuestionServiceImpl.listWatchQuestionsByUserId(user.getId());
            int totalPage = questions.size() / pageSize;
            if (questions.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
            questions = questions.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("questions", questions);
            request.setAttribute("totalPage", totalPage);
        }

        int likeCount = userResponseAnswerServiceImpl.getLikeResponseCountByUserId(user.getId());
        int dislikeCount = userResponseAnswerServiceImpl.getDislikeResponseCountByUserId(user.getId());

        List<Favourite> favourites = favouriteServiceImpl.listFavouriteByUserId(user.getId());
        
        long followerCount=followServiceImpl.listFollowByFollowingId(user.getId()).size();
        long followingCount=followServiceImpl.listFollowByFollowerId(user.getId()).size();
        
        request.setAttribute("favourites", favourites);
        request.setAttribute("likeCount", likeCount);
        request.setAttribute("dislikeCount", dislikeCount);
        request.setAttribute("followerCount", followerCount);
        request.setAttribute("followingCount", followingCount);
        
        return "personal";
    }

    @RequestMapping(value = "/userPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String userPage(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("id"));
        if (userId == 0)
            userId = (Long) request.getAttribute("id");
        HttpSession session;
        session = request.getSession();
        if (session.getAttribute("user") != null && ((User) session.getAttribute("user")).getId() == userId)
            return "forward:/userController/personal";

        user = userServiceImpl.getUserById(userId);
       
        int curPage = 1;
        if (request.getParameter("curPage") != null)
            curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = 5;

        String act = request.getParameter("act");
        if (act == null || act.equals("que")) {
            List<Question> questions = questionServiceImpl.listAllQuestionByUserId(user.getId());
            int totalPage = questions.size() / pageSize;
            if (questions.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
            questions = questions.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("questions", questions);
            request.setAttribute("totalPage", totalPage);
        } else if (act.equals("ans")) {
            List<Answer> answers = answerServiceImpl.listAnswerByUserId(user.getId());
            int totalPage = answers.size() / pageSize;
            if (answers.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= answers.size() ? front + pageSize : answers.size();
            answers = answers.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("answers", answers);
            request.setAttribute("totalPage", totalPage);
        } else {
            List<Question> questions = userWatchQuestionServiceImpl.listWatchQuestionsByUserId(user.getId());
            int totalPage = questions.size() / pageSize;
            if (questions.size() % pageSize != 0)
                totalPage += 1;
            int front = (curPage - 1) * pageSize;
            int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
            questions = questions.subList(front, end);
            request.setAttribute("curPage", curPage);
            request.setAttribute("questions", questions);
            request.setAttribute("totalPage", totalPage);
        }

        int likeCount = userResponseAnswerServiceImpl.getLikeResponseCountByUserId(user.getId());
        int dislikeCount = userResponseAnswerServiceImpl.getDislikeResponseCountByUserId(user.getId());

        List<Favourite> favourites = favouriteServiceImpl.listFavouriteByUserId(user.getId());
        request.setAttribute("user", user);
        request.setAttribute("favourites", favourites);
        request.setAttribute("likeCount", likeCount);
        request.setAttribute("dislikeCount", dislikeCount);
        user = (User) request.getSession().getAttribute("user");
        if(user!=null)
            request.setAttribute("follow", followServiceImpl.getFollowByFollwerAndFollwing(user.getId(), userId));
        return "userpage";
    }

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    public String follow(HttpServletRequest request) {
        Long fanId = ((User) request.getSession().getAttribute("user")).getId();
        Long id = Long.parseLong(request.getParameter("id"));
        follow.setFollowerId(fanId);
        follow.setFollowingId(id);
        follow.setCreateTime(new Date());
        follow.setModifiedTime(follow.getCreateTime());
        followServiceImpl.insertFollow(follow);
        request.setAttribute("id", id);
        return "forward:/userController/userPage";
    }

    @RequestMapping(value = "/deleteFollow", method = RequestMethod.GET)
    public String deleteFollow(HttpServletRequest request) {
        Long fanId = ((User) request.getSession().getAttribute("user")).getId();
        Long id = Long.parseLong(request.getParameter("id"));
        followServiceImpl.deleteFollow(fanId, id);

        request.setAttribute("id", id);
        return "forward:/userController/userPage";
    }
    
    @RequestMapping(value="/toFollower",method=RequestMethod.GET)
    public String toFollower(HttpServletRequest request) {
        user = (User)request.getSession().getAttribute("user");
        request.setAttribute("followers", followServiceImpl.listFollowByFollowingId(user.getId()));
        return "followlist";
    }
    
    @RequestMapping(value="/toFollowing",method=RequestMethod.GET)
    public String toFollowing(HttpServletRequest request) {
        user = (User)request.getSession().getAttribute("user");
        request.setAttribute("followings", followServiceImpl.listFollowByFollowerId(user.getId()));
        return "followlist";
    }
}
