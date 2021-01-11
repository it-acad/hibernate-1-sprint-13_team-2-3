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
public class StateTests {
    static private State validState;

    @BeforeAll
    static void init(){
        validState.setName("open");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStateName")
    void constraintViolationInvalid(String input, String errorValue) {
        State state = new State();
        state.setName(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidStateName(){
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
                Arguments.of("test?", "test?"),
                Arguments.of("test_21@", "test_21@"),
                Arguments.of("помилка", "помилка")
        );
    }


    @ParameterizedTest
    @MethodSource("provideValidStateName")
    void constraintViolationValid(String input) {
        State state = new State();
        state.setName(input);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);
        assertEquals(0, violations.size());
    }

    private static Stream<Arguments> provideValidStateName(){
        return Stream.of(
                Arguments.of("2"),
                Arguments.of("_"),
                Arguments.of("valid_123-"),
                Arguments.of("123valid"),
                Arguments.of("va lid")
        );
    }
}
