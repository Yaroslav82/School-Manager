package com.hillel.multi.enitities;

import com.hillel.multi.persistent.entities.Student;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class StudentValidationTests {

    private Validator validator;
    private Student student;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        student = new Student(null, "Luke", "Skywalker", "GD-123", null);
    }

    @Test
    public void testInvalidFirstName() {
        student.setFirstName("   ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(student);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidLastName() {
        student.setLastName("  ");
        Set<ConstraintViolation<Object>> constraints = validator.validate(student);
        Assertions.assertEquals(1, constraints.size());
    }

    @Test
    public void testInvalidGroupName() {
        student.setGroupName("DDDD-123");
        Set<ConstraintViolation<Object>> constraints = validator.validate(student);
        Assertions.assertEquals(1, constraints.size());
    }
}
