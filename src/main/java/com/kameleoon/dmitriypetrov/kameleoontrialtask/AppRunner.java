package com.kameleoon.dmitriypetrov.kameleoontrialtask;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.QuoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;

@Component
public class AppRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;
    private final PasswordEncoder passwordEncoder;

    public AppRunner(UserRepository userRepository, QuoteRepository quoteRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.quoteRepository = quoteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User u1 = User.builder().name("John").email("john@gmail.com")
                .password(passwordEncoder.encode("123")).createDate(LocalDate.now()).build();
        User u2 = User.builder().name("Dmitriy").email("dima@gmail.com")
                .password(passwordEncoder.encode("777")).createDate(LocalDate.now()).build();
        userRepository.save(u1);
        userRepository.save(u2);
        Quote q1 = Quote.builder().user(u1).content("hello")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q2 = Quote.builder().user(u1).content("bye")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q3 = Quote.builder().user(u2).content("Zenit - champion !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q4 = Quote.builder().user(u2).content("Hi !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q5 = Quote.builder().user(u2).content("I love Java !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q6 = Quote.builder().user(u2).content("Docker the best !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q7 = Quote.builder().user(u2).content("I like this job !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q8 = Quote.builder().user(u2).content("Improve")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q9 = Quote.builder().user(u2).content("Spring - the best framework!")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q10 = Quote.builder().user(u2).content("Use Jpa !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        Quote q11 = Quote.builder().user(u2).content("GoodBye !")
                .createDate(new Timestamp(System.currentTimeMillis())).build();
        quoteRepository.save(q1);
        quoteRepository.save(q2);
        quoteRepository.save(q3);
        quoteRepository.save(q4);
        quoteRepository.save(q5);
        quoteRepository.save(q6);
        quoteRepository.save(q7);
        quoteRepository.save(q8);
        quoteRepository.save(q9);
        quoteRepository.save(q10);
        quoteRepository.save(q11);


    }

}
