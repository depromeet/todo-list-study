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
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public List<Todo> findAllTodo(String name) {
        User user = userRepository.findById(name).orElse(null);
        assert user != null;
        return user.getTodos().getTodoList();
    }


    public Todo findTodo(String name, Long todoId) {
        return todoRepository.findById(todoId).orElse(null);
    }


    public Todo addTodo(String name, RequestTodoDto requestTodoDto) {
        User user = userRepository.findById(name).orElse(null);
        assert user != null;
        Todo todo = new Todo(requestTodoDto.getTitle(), user);
        return todoRepository.save(todo);
    }

    @Transactional
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
