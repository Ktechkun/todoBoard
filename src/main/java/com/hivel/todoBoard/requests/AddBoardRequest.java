package com.hivel.todoBoard.requests;

import com.hivel.todoBoard.entity.Users;


public class AddBoardRequest {
    private String name;
    private Users user;

    public AddBoardRequest() {
    }

    public AddBoardRequest(String name, Users user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
