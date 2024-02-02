package com.hillel.multi.service;

import com.hillel.multi.configuration.TestSecurityConfig;
import com.hillel.multi.persistent.entities.Homework;
import com.hillel.multi.persistent.repositories.HomeworkManagerRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = HomeworkManagerService.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestSecurityConfig.class)
public class HomeworkManagerServiceTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HomeworkManagerService service;

    @MockBean
    private HomeworkManagerRepository repository;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testEntityToDtoValidation() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.entityToDTO(new Homework()));
    }
}
