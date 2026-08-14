package com.kameleoon.dmitriypetrov.kameleoontrialtask.service.user;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.RegisterUserRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.exception.IncorrectDataException;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByLogin(LoginRq loginRq) {
        User user = userRepository.findByName(loginRq.getName()).orElse(null);
        if (user == null) {
            return null;
        } else if (!passwordEncoder.matches(loginRq.getPassword(), user.getPassword())) {
            throw new IncorrectDataException("wrong password");
        }
        return user;
    }

    @Override
    @Transactional
    public User registerUser(RegisterUserRq registerUserRq) {
        Optional<User> existingUserByName = userRepository.findByName(registerUserRq.getName());
        if (existingUserByName.isPresent()) {
            throw new IncorrectDataException("User with this name already exists");
        }
        
        Optional<User> existingUserByEmail = userRepository.findByEmail(registerUserRq.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IncorrectDataException("User with this email already exists");
        }
        
        User user = User.builder()
                .name(registerUserRq.getName())
                .email(registerUserRq.getEmail())
                .password(passwordEncoder.encode(registerUserRq.getPassword()))
                .createDate(LocalDate.now())
                .build();
        
        return userRepository.save(user);
    }
}
