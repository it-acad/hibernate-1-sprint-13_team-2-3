package com.softserve.itacademy.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "tasks")
public class Task {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Length(min = 3, max = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne()
    @JoinColumn(name = "todo_id")
    private ToDo toDo;

    @OneToOne(cascade = CascadeType.ALL)
    private State state;

    public Task() {
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
