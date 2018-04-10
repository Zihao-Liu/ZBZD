package cn.lzh.zbzd.service;

import cn.lzh.zbzd.model.User;

public interface UserService {
    public int signUp(User user);

    public User signIn(String username, String password);

    public void signOut();

    public User checkExistUsername(String username);

    public User getUserById(Long id);
}
