package com.kameleoon.dmitriypetrov.kameleoontrialtask.controller;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.RegisterUserRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.IncorrectDataException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRq loginRq){
        User user = userService.getUserByLogin(loginRq);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserRq registerUserRq){
        try {
            User user = userService.registerUser(registerUserRq);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IncorrectDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
