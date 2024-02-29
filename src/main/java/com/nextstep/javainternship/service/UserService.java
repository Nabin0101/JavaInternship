package com.nextstep.javainternship.service;

import com.nextstep.javainternship.entity.User;

public interface UserService {
    public User saveUser(User user);
    public User findUserById(int id);
    public void updateUser(User user, int id);
    public void deleteUser(int id);
    public User getUserNameAndPassword(String username, String password);
}
