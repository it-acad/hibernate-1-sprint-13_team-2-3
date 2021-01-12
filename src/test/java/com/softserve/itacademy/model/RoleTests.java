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
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoleTests {
    static Role validRole;

    @BeforeAll
    static void init() {
        validRole.setName("role");
//        validRole.setUsers(new ArrayList<User>());
    }

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role role = new Role();
        role.setName("");
//        role.setUsers(validRole.getUsers());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        assertEquals(1, violations.size());
    }
}
