package com.mschoeffel.mydms.service;

import com.mschoeffel.mydms.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(String username);

    public User save(User user);

    public boolean existsId(String username);

    public void deleteById(String username);

}
