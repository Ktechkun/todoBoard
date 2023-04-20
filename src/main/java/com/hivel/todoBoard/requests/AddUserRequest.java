package com.hivel.todoBoard.requests;

public class AddUserRequest {
    private String username;

    public AddUserRequest(String username) {
        this.username = username;
    }

    public AddUserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
