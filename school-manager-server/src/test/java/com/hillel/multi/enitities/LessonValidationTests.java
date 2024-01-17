package com.hillel.multi.enitities;

import com.hillel.multi.persistent.entities.Lesson;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class LessonValidationTests {

    private Validator validator;
    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        lesson = new Lesson(null, "2024-01-10 11:20:00", "GTR-123", "Math");
    }

    @Test
    public void testInvalidTimestamp() {
        lesson.setTimestamp("abcd");
        Set<ConstraintViolation<Object>> constraints = validator.validate(lesson);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidGroupName() {
        lesson.setGroupName("DDDD-123");
        Set<ConstraintViolation<Object>> constraints = validator.validate(lesson);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidSubject() {
        lesson.setSubject("   ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(lesson);
        Assertions.assertEquals(1, constraints.size());
    }
}
