package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
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
public class TaskTests {
    private static Task validTask;

    @BeforeAll
    static void init(){
        validTask = new Task();
        validTask.setName("task");
        validTask.setPriority(Priority.HIGH);
        validTask.setState(new State());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTaskName")
    void constraintViolationInvalid(String input, String errorValue) {
        Task task = new Task();
        task.setName(input);
        task.setPriority(validTask.getPriority());
        task.setState(validTask.getState());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidTaskName(){
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("a", "a"),
                Arguments.of("ab", "ab")
        );
    }


    @ParameterizedTest
    @MethodSource("provideValidTaskName")
    void constraintViolationValid(String input) {
        Task task = new Task();
        task.setName(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidTaskName(){
        return Stream.of(
                Arguments.of("adssa"),
                Arguments.of("213asdas"),
                Arguments.of("???asfasd#$%^")
        );
    }


}
