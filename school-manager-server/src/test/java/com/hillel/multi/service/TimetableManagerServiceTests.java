package com.hillel.multi.service;

import com.hillel.multi.persistent.entities.Lesson;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = TimetableManagerService.class)
public class TimetableManagerServiceTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TimetableManagerService service;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testEntityToDtoValidation() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.entityToDTO(new Lesson()));
    }
}