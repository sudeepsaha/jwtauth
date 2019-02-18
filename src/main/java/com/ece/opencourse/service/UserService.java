package com.ece.opencourse.service;

import com.ece.opencourse.entity.UserRole;
import com.ece.opencourse.model.User;
import com.ece.opencourse.repository.UserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRoleRepository userRoleRepository;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        List<UserRole> userRoles = userRoleRepository.findAll();
        if (userRoles != null) {
            userRoles.forEach(userRole -> {
                users.add(new User(
                        userRole.getUserName(),
                        userRole.getFirstName(),
                        userRole.getLastName(),
                        userRole.getEmail(),
                        userRole.getPhNo(),
                        userRole.getRole()));
            });
        }
        return users;
    }
}
