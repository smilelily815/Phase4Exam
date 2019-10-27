package com.itheima.service;

import com.itheima.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User findById(Long id);

    public void update(User user);

    public User findByIdAndName(String username, String password);
}
