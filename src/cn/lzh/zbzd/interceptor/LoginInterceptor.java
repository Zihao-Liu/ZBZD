package cn.lzh.zbzd.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object user = request.getSession().getAttribute("user");
        String requestURL = request.getRequestURI();
        List<String> forbiddenURLS = new ArrayList<String>();
        forbiddenURLS.add("/answerController/postAnswer");
        forbiddenURLS.add("/questionController/postQuestion");
        forbiddenURLS.add("/userController/personal");
        forbiddenURLS.add("questionController/editQuestionAnonymous");
        forbiddenURLS.add("answerController/editAnswerAnonymous");
        forbiddenURLS.add("/answerController/toPostAnswer");
        forbiddenURLS.add("/answerController/responseAnswer");
        forbiddenURLS.add("/favouriteController/insertFavourite");
        forbiddenURLS.add("/questionController/watchQuestion");
        forbiddenURLS.add("/userController/follow");
        forbiddenURLS.add("/userController/deleteFollow");
        forbiddenURLS.add("/userController/toFollowing");
        forbiddenURLS.add("/userController/toFollower");
        for (String forbiddenURL : forbiddenURLS) {
            if (requestURL.contains(forbiddenURL) && null == user) {
                response.sendRedirect("/zbzd/userController/toSignIn");
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
            ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e)
            throws Exception {

    }
}
