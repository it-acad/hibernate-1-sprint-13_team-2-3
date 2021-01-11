package com.softserve.itacademy.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Length(min = 3, max = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToOne
    @JoinColumn(name = "todo_id")
    private ToDo toDo;

    @OneToOne(cascade = CascadeType.ALL)
    private State state;

    public Task() {
    }

    public Task(@Length(min = 3, max = 200) String name, Priority priority, ToDo toDo, State state) {
        this.name = name;
        this.priority = priority;
        this.toDo = toDo;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                priority == task.priority &&
                Objects.equals(toDo, task.toDo) &&
                Objects.equals(state, task.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, priority, toDo, state);
    }
}
