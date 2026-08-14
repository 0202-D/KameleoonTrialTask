package com.kameleoon.quote;

import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.Quote;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.entity.User;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.QuoteRepository;
import com.kameleoon.dmitriypetrov.kameleoontrialtask.dao.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Base64;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = com.kameleoon.dmitriypetrov.kameleoontrialtask.KameleoonTrialTaskApplication.class)
@Testcontainers
@ActiveProfiles("test")
public class ContainerTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    private String authHeader;

    @BeforeEach
    void setUp() {
        // Очищаем базу перед каждым тестом
        quoteRepository.deleteAll();
        userRepository.deleteAll();

        // Создаем тестового пользователя
        User user = new User();
        user.setName("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("{noop}password"); // noop для простоты в тестах
        user.setCreateDate(java.time.LocalDate.now());
        userRepository.save(user);

        // Создаем заголовок авторизации (Basic Auth)
        String auth = "testuser:password";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        authHeader = "Basic " + encodedAuth;
    }

    @Test
    void whenGetUser_thenCorrect() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                "/api/users/testuser",
                HttpMethod.GET,
                entity,
                User.class
        );

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("testuser", response.getBody().getName());
    }

    @Test
    void addQuote() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> quoteBody = Map.of(
                "content", "Test Quote"
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(quoteBody, headers);

        ResponseEntity<Quote> response = restTemplate.exchange(
                "/api/quotes",
                HttpMethod.POST,
                entity,
                Quote.class
        );

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Test Quote", response.getBody().getContent());
    }

    @Test
    void addQuoteBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Пустое тело или некорректные данные
        Map<String, Object> quoteBody = Map.of();

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(quoteBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/quotes",
                HttpMethod.POST,
                entity,
                String.class
        );

        assertEquals(400, response.getStatusCodeValue());
    }
}
