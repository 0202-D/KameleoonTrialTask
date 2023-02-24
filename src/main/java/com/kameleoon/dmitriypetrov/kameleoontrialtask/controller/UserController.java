package com.kameleoon.dmitriypetrov.kameleoontrialtask.controller;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRq loginRq){
        User user = userService.getUserByLogin(loginRq);
        if(user==null){return new ResponseEntity(HttpStatus.NOT_FOUND);}
        return new ResponseEntity(HttpStatus.OK);
    }

}
