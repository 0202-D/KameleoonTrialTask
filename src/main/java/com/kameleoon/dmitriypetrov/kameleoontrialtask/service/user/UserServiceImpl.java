package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.user;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.IncorrectDataException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(LoginRq loginRq) {
        User user =  userRepository.findByName(loginRq.getName());
        if(user==null){return null;}
        else if(!user.getPassword().equals(loginRq.getPassword())){
            throw new IncorrectDataException("wrong password");
        }
        return user;

    }


}
