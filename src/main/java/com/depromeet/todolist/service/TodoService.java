package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.TodoRepository;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodo(User user, RequestTodoDto requestTodoDto) {
        Todo todo = new Todo(requestTodoDto.getTitle(), user);
        return todoRepository.save(todo);
    }

    public Todo updateTodoTitle(Long todoId, String newTitle) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        assert todo != null;
        todo.updateTitle(newTitle);
        return todo;
    }


    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
        }
    }
}
