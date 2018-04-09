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

import cn.lzh.zbzd.model.Question;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.serviceimpl.QuestionServiceImpl;
import cn.lzh.zbzd.serviceimpl.UserServiceImpl;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private User user;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

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
            request.setAttribute("error", "�û�������Ϊ��");
            return "signup";
        } else {
            m = r.matcher(username);
            if (!m.matches()) {
                request.setAttribute("error", "�û���ֻ��Ϊ���ֺ���ĸ");
                return "signup";
            }
        }

        if (password.equals(null) || password == "") {
            request.setAttribute("error", "���벻��Ϊ��");
            return "signup";
        } else {
            m = r.matcher(password);
            if (!m.matches()) {
                request.setAttribute("error", "����ֻ��Ϊ���ֺ���ĸ");
                return "signup";
            }
        }

        if (nickname.equals(null) || nickname == "") {
            request.setAttribute("error", "�ǳƲ���Ϊ��");
            return "signup";
        } else {
            m = r.matcher(nickname);
            if (!m.matches()) {
                request.setAttribute("error", "�ǳ�ֻ��Ϊ���ֺ���ĸ");
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
            request.setAttribute("error", "�û����Ѵ���");
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
            request.setAttribute("error", "�û�������Ϊ��");
            return "signin";
        } else {
            m = r.matcher(username);
            if (!m.matches()) {
                request.setAttribute("error", "�û���ֻ��Ϊ���ֺ���ĸ");
                return "signin";
            }
        }

        if (password.equals(null) || password == "") {
            request.setAttribute("error", "���벻��Ϊ��");
            return "signin";
        } else {
            m = r.matcher(password);
            if (!m.matches()) {
                request.setAttribute("error", "����ֻ��Ϊ���ֺ���ĸ");
                return "signin";
            }
        }

        User user = userServiceImpl.signIn(username, password);
        if (user == null) {
            request.setAttribute("error", "�û������������");
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

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String personal(HttpServletRequest request) {
        HttpSession session;
        session = request.getSession();
        user = (User) session.getAttribute("user");
        List<Question> questions = questionServiceImpl.listAllQuestionByUserId(user.getId());
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int totalPage = questions.size() / pageSize;
        if (questions.size() % pageSize != 0)
            totalPage += 1;
        int front = (curPage - 1) * pageSize;
        int end = front + pageSize <= questions.size() ? front + pageSize : questions.size();
        questions = questions.subList(front, end);
        request.setAttribute("curPage", curPage);
        request.setAttribute("questions", questions);
        request.setAttribute("totalPage", totalPage);
        return "personal";
    }
}
