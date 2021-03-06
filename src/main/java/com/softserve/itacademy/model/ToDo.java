package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotBlank(message = "The title cannot be empty")
    private String title;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "collaboration_todo")
    private List<User> collaborators;

    @OneToMany(mappedBy = "toDo")
    private Set<Task> taskList;

    @ManyToOne
    private User owner;

    public ToDo() {
        createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
