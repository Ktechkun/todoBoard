package com.hivel.todoBoard.requests;

import com.hivel.todoBoard.entity.Board;
import com.hivel.todoBoard.entity.Users;

public class AddTodoRequest {
    private String description;
    private Users user;
    private Board board;
    private Boolean isCompleted;


    public AddTodoRequest() {
    }

    public AddTodoRequest(String description, Users user, Board board, Boolean isCompleted) {
        this.description = description;
        this.user = user;
        this.board = board;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
