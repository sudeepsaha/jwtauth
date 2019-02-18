package com.ece.opencourse.controller;

import com.ece.opencourse.model.User;
import com.ece.opencourse.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity(users, OK);
    }
}
