package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.user;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserByLogin(LoginRq loginRq);

}
