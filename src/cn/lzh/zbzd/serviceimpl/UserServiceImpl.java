package cn.lzh.zbzd.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lzh.zbzd.dao.UserDao;
import cn.lzh.zbzd.model.User;
import cn.lzh.zbzd.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private User user;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int signUp(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User signIn(String username, String password) {
        user = userDao.getUserByUsername(username);
        if (user == null)
            return null;
        else if (!user.getPassword().equals(password))
            return null;
        user.setSignInTime(new Date());
        user.setModifiedTime(user.getSignInTime());
        userDao.updateUserSignInTime(user);
        return user;
    }

    @Override
    public void signOut() {
        // TODO Auto-generated method stub

    }

    @Override
    public User checkExistUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

}
