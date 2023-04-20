package com.hivel.todoBoard.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Board> boards = new HashSet<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> assignedTodos = new ArrayList<>();

    public Users() {
    }

    public Users(Long id, String name, Set<Board> boards, List<Todo> assignedTodos) {
        this.id = id;
        this.name = name;
        this.boards = boards;
        this.assignedTodos = assignedTodos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Board> getBoards() {
        return boards;
    }

    public void setBoards(Set<Board> boards) {
        this.boards = boards;
    }

    public List<Todo> getAssignedTodos() {
        return assignedTodos;
    }

    public void setAssignedTodos(List<Todo> assignedTodos) {
        this.assignedTodos = assignedTodos;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", boards=" + boards +
                ", assignedTodos=" + assignedTodos +
                '}';
    }

    public void receiveNotification(String message) {
        System.out.println("User "+this.name+ " got notification : "+ message);
    }
}
