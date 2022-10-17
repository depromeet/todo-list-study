package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.Todos;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.repository.TodoRepository;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final CommonService commonService;
    private final TodoRepository todoRepository;


    public ResponseTodoDto getTodo(String name, Long todoId) {
        User user = commonService.findUserByIdIfExists(name);
//        Todos todoList = user.getTodos();
//        Todo todo = todoList.checkUserContainsTodo(todoId);
        Todo byId = todoRepository.getById(todoId);
        return todoEntityToDto(byId);
    }


    public List<ResponseTodoDto> getUserTodoList(String name) {
        User user = commonService.findUserByIdIfExists(name);
        List<Todo> allTodos = todoRepository.findAllTodos(name);
//        Todos todoList = user.getTodos();
        return todoListToTodoDtoList(allTodos);
    }


    public ResponseTodoDto addTodo(String name, RequestTodoDto requestTodoDto) {
//        User user = commonService.findUserByIdIfExists(name);
        Todo todo = new Todo(requestTodoDto.getTitle(), name);
        Todo savedTodo = todoRepository.save(todo);
        return todoEntityToDto(savedTodo);
    }


    public ResponseTodoDto updateTodoTitle(String name, Long todoId, String newTitle) {
        User user = commonService.findUserByIdIfExists(name);
//        Todo todo = user.getTodos().checkUserContainsTodo(todoId);
        Todo byId = todoRepository.getById(todoId);
        byId.updateTitle(newTitle);
        return todoEntityToDto(byId);
    }


    public void deleteTodo(String name, Long todoId) {
        User user = commonService.findUserByIdIfExists(name);
//        Todo todo = user.getTodos().checkUserContainsTodo(todoId);
        todoRepository.deleteById(todoId);
    }


    private ResponseTodoDto todoEntityToDto(Todo todo) {
        return new ResponseTodoDto(todo.getId(), todo.getTitle());
    }


    private List<ResponseTodoDto> todoListToTodoDtoList(List<Todo> todos) {
        return todos.stream().map(this::todoEntityToDto).collect(Collectors.toList());
    }
}
