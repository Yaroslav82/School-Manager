package com.hillel.multi.mappers;

import com.hillel.model.HomeworkModel;
import com.hillel.multi.persistent.entities.Homework;
import com.hillel.multi.service.mappers.HomeworkMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = HomeworkMapper.class)
public class HomeworkMapperTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HomeworkMapper mapper;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void testEntityToModelInvalid() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> mapper.entityToModel(new Homework()));
    }

    @Test
    public void testModelToEntityInvalid() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> mapper.modelToEntity(new HomeworkModel()));
    }

    @Test
    public void testEntityToModelValid() {
        Homework homework = new Homework(null, "HW1", "Paragraph 1", "DDD-123", "Math", "2024-01-23", null);
        HomeworkModel model = mapper.entityToModel(homework);

        Assertions.assertEquals("HW1", model.getName());
        Assertions.assertEquals("Paragraph 1", model.getDescription());
        Assertions.assertEquals("DDD-123", model.getGroup());
        Assertions.assertEquals("Math", model.getSubject());
        Assertions.assertEquals("2024-01-23", model.getDeadline());
    }

    @Test
    public void testModelToEntityValid() {
        HomeworkModel model = new HomeworkModel();
        model.name("HW1");
        model.description("Paragraph 1");
        model.group("DDD-123");
        model.subject("Math");
        model.deadline("2024-01-23");

        Homework homework = mapper.modelToEntity(model);

        Assertions.assertEquals("HW1", homework.getName());
        Assertions.assertEquals("Paragraph 1", homework.getDescription());
        Assertions.assertEquals("DDD-123", homework.getGroupName());
        Assertions.assertEquals("Math", homework.getSubject());
        Assertions.assertEquals("2024-01-23", homework.getDeadline());
    }
}
