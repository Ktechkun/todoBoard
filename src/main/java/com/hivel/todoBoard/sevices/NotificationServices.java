package com.hivel.todoBoard.sevices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivel.todoBoard.entity.Board;
import com.hivel.todoBoard.entity.Todo;
import com.hivel.todoBoard.entity.Users;
import com.hivel.todoBoard.models.TodoItemUpdateModel;
import com.hivel.todoBoard.repository.BoardRepository;
import com.hivel.todoBoard.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class NotificationServices {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TodoRepository todoRepository;

    @KafkaListener(topics = "todo-list-updates")
    public void processTodoListNotification(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        TodoItemUpdateModel updateModel = null;
        try {
            updateModel = objectMapper.readValue(message, TodoItemUpdateModel.class);
        } catch (Exception e) {
            System.out.println("Something happened while reading the message");
            e.printStackTrace();
            return;
        }

        String notification = "Todo task - "+updateModel.getTodoItem().getDescription()+" is marked Completed = "+updateModel.getTodoItem().getCompleted().toString()+" by User "+updateModel.getUser().getName();
        System.out.println(notification);

    }

}

