package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.TodoList;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.TodoRepository;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

//    public TodoList findAllTodo(String userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        return user.getTodoList();
//    }


    public Todo findTodo(String name, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        return todo;
    }


    public Todo addTodo(String name, RequestTodoDto requestTodoDto) {
        Todo todo = new Todo(requestTodoDto.getTitle(),userRepository.getById(name));
        return todoRepository.save(todo);
    }


    public Todo updateTodoTitle(Long todoId, String newTitle) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo == null) {
            return null;
        }
        todo.updateTitle(newTitle);
        return todo;
    }


    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
        }
    }

    public boolean isUserContainsTodo(String name, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        User user = userRepository.findById(name).orElse(null);
        if (user == null || todo == null) return false;
        List<Todo> todoList = user.getTodoList().getTodoList();
        if (todoList.contains(todo)) return true;
        else return false;
    }
}
