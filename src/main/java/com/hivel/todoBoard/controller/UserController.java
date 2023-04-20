package com.hivel.todoBoard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivel.todoBoard.entity.Board;
import com.hivel.todoBoard.entity.Todo;
import com.hivel.todoBoard.entity.Users;
import com.hivel.todoBoard.models.TodoItemUpdateModel;
import com.hivel.todoBoard.repository.BoardRepository;
import com.hivel.todoBoard.repository.TodoRepository;
import com.hivel.todoBoard.repository.UserRepository;
import com.hivel.todoBoard.requests.AddBoardRequest;
import com.hivel.todoBoard.requests.AddTodoRequest;
import com.hivel.todoBoard.requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/{userId}")
    public Users getUserIdById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public Users addUser(@RequestBody AddUserRequest userRequest){
        Users user = new Users();
        user.setName(userRequest.getUsername());
//        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/board")
    public Board addBoard(@PathVariable Long userId, @RequestBody AddBoardRequest boardRequest){
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Board board = new Board();
        board.setName(boardRequest.getName());
        board.setUser(user);
        user.getBoards().add(board);
        return boardRepository.save(board);
    }

    @DeleteMapping("{userId}/board/{boardId}")
    public void deleteBoard(@PathVariable Long userId,@PathVariable Long boardId) throws IllegalAccessException {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        if(!board.getUser().getName().equals(user.getName())) throw new IllegalAccessException();

        user.getBoards().remove(board);
        boardRepository.delete(board);
    }

    @PostMapping("/{userId}/board/{boardId}/todo")
    public Todo addTodoBoard(@PathVariable Long userId,@PathVariable Long boardId, @RequestBody AddTodoRequest todoRequest) throws IllegalAccessException {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        if(!board.getUser().getName().equals(user.getName())) throw new IllegalAccessException();

        Todo todo = new Todo();
        todo.setUsers(todoRequest.getUser());
        todo.setDescription(todoRequest.getDescription());
        todo.setBoard(board);
        return todoRepository.save(todo);
    }

    @PutMapping("/{userId}/todos/{todoId}")
    public Todo toggleTodoCompleted(@PathVariable Long userId, @PathVariable Long todoId) throws IllegalAccessException {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);

        if (!todo.getUsers().getName().equals(user.getName())) throw new IllegalAccessException();

        todo.setCompleted(!todo.getCompleted());
        todo = todoRepository.save(todo);
        try {
            TodoItemUpdateModel message = new TodoItemUpdateModel(todo, null, user);
            ObjectMapper objectMapper = new ObjectMapper();
            String stringMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("todo-list-updates", stringMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todo;
    }

    @DeleteMapping("{userId}/board/{boardId}/todo/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long boardId, @PathVariable Long todoId) throws IllegalAccessException {
        Users user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);
        Todo todo = todoRepository.findById(todoId).orElseThrow(NoSuchElementException::new);

        if(!(board.getUser().getName().equals(user.getName()) || todo.getUsers().getName().equals(user.getName()))) throw new IllegalAccessException();

        board.getTodos().remove(todo);
        todoRepository.delete(todo);
    }
}
