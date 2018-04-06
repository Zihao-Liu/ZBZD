package cn.lzh.zbzd.dao;


import cn.lzh.zbzd.model.User;

public interface UserDao {
    public int insertUser(User user);
    public User getUserById(long id);
    public User getUserByUsername(String username);
    public User listUserByNickName(String nickname);
    public User updateUser(User user);
}
