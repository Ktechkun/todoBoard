package com.hivel.todoBoard.repository;

import com.hivel.todoBoard.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
