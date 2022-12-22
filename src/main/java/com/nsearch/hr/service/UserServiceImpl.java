package com.nsearch.hr.service;

import com.nsearch.hr.entity.User;
import com.nsearch.hr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUserList(float minSalary, float maxSalary, int offset, int limit){
        if (limit == 0){
            limit = Integer.MAX_VALUE;
        }
        Pageable pageableResult = PageRequest.of(offset, limit, Sort.by("id"));

        List<User> userList =  (List<User>) userRepository.findAll(UserSpecs.hasMinimumSalary(minSalary)
                .and(UserSpecs.hasMaximumSalary(maxSalary)),pageableResult).toList();

        return userList;
    }

    @Override
    public User getUserById(String id){
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUserById(User user, String id){
        User userToUpdate = userRepository.findById(id).get();

        if(Objects.nonNull(userToUpdate)){
            userToUpdate.setLogin(user.getLogin());
            userToUpdate.setName(user.getName());
            userToUpdate.setSalary(user.getSalary());
            userToUpdate.setStartDate(user.getStartDate());
        }
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }
}
