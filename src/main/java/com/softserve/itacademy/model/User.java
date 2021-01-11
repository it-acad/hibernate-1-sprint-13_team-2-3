package com.softserve.itacademy.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<ToDo> toDoList;

//
//    @JoinTable(
//            name = "todo_collaborator",
//            joinColumns = @JoinColumn(
//                    name = "todo_id",
//                    referencedColumnName = "collaboration_todo"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "collaborator_id",
//                    referencedColumnName = "collaborators"
//            )
//    )
    @ManyToMany
    private List<ToDo> collaboration_todo;

    public BigInteger getId() {
        return id;
    }

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
