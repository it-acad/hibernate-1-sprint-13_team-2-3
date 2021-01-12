package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoTests {
    private static ToDo validToDo;
    private static ToDo invalidToDo;

    @BeforeAll
    static void init(){
        validToDo = new ToDo();
        validToDo.setTitle("Title");
        validToDo.setOwner(new User());

        invalidToDo = new ToDo();
        invalidToDo.setTitle("");
        invalidToDo.setOwner(new User());
    }


    @Test
    void constraintViolationInvalid() {
        ToDo toDo = new ToDo();
        toDo.setTitle(invalidToDo.getTitle());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
        assertEquals(1, violations.size());
    }

    @Test
    void constraintViolationValid() {
        ToDo toDo = new ToDo();
        toDo.setTitle(validToDo.getTitle());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);
        assertEquals(0, violations.size());
    }

}
