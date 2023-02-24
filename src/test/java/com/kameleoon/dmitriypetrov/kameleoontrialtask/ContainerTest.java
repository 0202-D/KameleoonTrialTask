package com.kameleoon.dmitriypetrov.kameleoontrialtask;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.quote.AddQuoteRq;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dto.user.LoginRq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerTest {
    //several tests to demonstrate skills
    @Autowired
    TestRestTemplate template;
    public static GenericContainer<?> app = new GenericContainer("kameleoontrialtask_app").withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        app.start();
    }

    @Test
    public void whenGetUser_thenCorrect() {
        LoginRq loginRq = LoginRq.builder().name("Dmitriy").password("777").build();

        ResponseEntity<String> response = template
                .postForEntity("/login", loginRq, String.class);

        var expected = HttpStatus.OK.value();
        var actual = response.getStatusCodeValue();
        Assertions.assertEquals(expected, actual);
    }
     @Test
    public void addQuote(){
         AddQuoteRq addQuoteRq = AddQuoteRq.builder().userId(1).content("Hello").build();
        ResponseEntity<String>response = template.postForEntity("/quote",addQuoteRq,String.class);
         var expected = HttpStatus.OK.value();
         var actual = response.getStatusCodeValue();
         Assertions.assertEquals(expected, actual);
     }
    @Test
    public void addQuoteBadRequest(){
        AddQuoteRq addQuoteRq = AddQuoteRq.builder().userId(5).content("Hello").build();
        ResponseEntity<String>response = template.postForEntity("/quote",addQuoteRq,String.class);
        var expected = HttpStatus.BAD_REQUEST.value();
        var actual = response.getStatusCodeValue();
        Assertions.assertEquals(expected, actual);
    }
}
