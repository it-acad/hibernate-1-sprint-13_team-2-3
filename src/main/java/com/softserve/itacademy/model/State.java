package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Pattern(regexp = "^[\\w\\s-]{1,20}$")
    private String name;

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
