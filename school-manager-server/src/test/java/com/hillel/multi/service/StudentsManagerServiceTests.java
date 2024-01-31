package com.hillel.multi.service;

import com.hillel.multi.configuration.TestSecurityConfig;
import com.hillel.multi.persistent.entities.Student;
import com.hillel.multi.persistent.repositories.StudentManagerRepository;
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

@WebMvcTest(controllers = StudentsManagerService.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestSecurityConfig.class)
public class StudentsManagerServiceTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StudentsManagerService service;

    @MockBean
    private StudentManagerRepository repository;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testEntityToDtoValidation() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.entityToDTO(new Student()));
    }
}
