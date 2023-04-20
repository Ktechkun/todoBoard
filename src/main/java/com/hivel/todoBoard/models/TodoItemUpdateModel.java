package com.hivel.todoBoard.models;

import com.hivel.todoBoard.entity.Board;
import com.hivel.todoBoard.entity.Todo;
import com.hivel.todoBoard.entity.Users;

public class TodoItemUpdateModel {
    private Todo todoItem;
    private Board board;
    private Users user;

    public TodoItemUpdateModel(Todo todoItem, Board board, Users user) {
        this.todoItem = todoItem;
        this.board = board;
        this.user = user;
    }

    public TodoItemUpdateModel() {
    }

    public Todo getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(Todo todoItem) {
        this.todoItem = todoItem;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
