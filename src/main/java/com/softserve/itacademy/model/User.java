package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^([A-Z])([a-z])+-([A-Z])([a-z])+$")
    private String firstName;

    @Pattern(regexp = "^([A-Z])([a-z])+-([A-Z])([a-z])+$")
    private String lastName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[!])(?=.*[A-Z]).{10,}$")
    private String password;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<ToDo> toDoList;


    public BigInteger getId() {
        return id;
    }

    @JoinTable(
            name = "todo_collaborator",
            joinColumns = @JoinColumn(
                    name = "collaborator_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "todo_id",
                    referencedColumnName = "id"
            )
    )
    @ManyToMany
    private List<ToDo> collaboration_todo;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
