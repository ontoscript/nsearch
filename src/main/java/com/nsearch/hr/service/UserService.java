package com.nsearch.hr.service;

import com.nsearch.hr.entity.User;

import java.util.List;

public interface  UserService {

    User saveUser(User user);

    List<User> fetchUserList(float minSalary, float maxSalary, int offset, int limit);

    User getUserById(String id);

    User updateUserById(User user, String id);

    void deleteUserById(String id);

}
