package com.kameleoon.dmitriypetrov.kameleoontrialtask;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.QuoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
@Component
public class AppRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    public AppRunner(UserRepository userRepository, QuoteRepository quoteRepository) {
        this.userRepository = userRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User u1 = User.builder().name("John").email("john@gmail.com")
                .password("123").createDate(LocalDate.now()).build();
         User u2 = User.builder().name("Dmitriy").email("dima@gmail.com")
                .password("777").createDate(LocalDate.now()).build();
        userRepository.save(u1);
        userRepository.save(u2);
        Quote q1 = Quote.builder().user(u1).content("hello")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q2 = Quote.builder().user(u1).content("bye")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q3 = Quote.builder().user(u2).content("Zenit - champion !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q4 = Quote.builder().user(u2).content("Hi !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q5 = Quote.builder().user(u2).content("I love Java !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q6 = Quote.builder().user(u2).content("Docker the best !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q7 = Quote.builder().user(u2).content("I like this job !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q8 = Quote.builder().user(u2).content("Improve")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q9 = Quote.builder().user(u2).content("Spring - the best framework!")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q10 = Quote.builder().user(u2).content("Use Jpa !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
        Quote q11 = Quote.builder().user(u2).content("GoodBye !")
                .createData(new Timestamp(System.currentTimeMillis())).build();
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
