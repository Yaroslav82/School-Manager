package com.hillel.multi.enitities;

import com.hillel.multi.persistent.entities.Homework;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class HomeworkValidationTests {

    private Validator validator;
    private Homework homework;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        homework = new Homework(null, "HW1", "Paragraph 1", "DDD-123", "Math", "2024-01-23", null);
    }

    @Test
    public void testInvalidName() {
        homework.setName("  ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(homework);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidDescription() {
        homework.setDescription("    ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(homework);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidGroupName() {
        homework.setGroupName("DDDD-123");
        Set<ConstraintViolation<Object>> constraints = validator.validate(homework);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidSubject() {
        homework.setSubject("    ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(homework);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidDeadline() {
        homework.setDeadline("2022-123-12");
        Set<ConstraintViolation<Object>> constraints = validator.validate(homework);
        Assertions.assertEquals(1, constraints.size());
    }
}
