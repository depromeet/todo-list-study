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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final CommonService commonService;
    private final TodoRepository todoRepository;

    public ResponseTodoDto getTodo(String name, Long todoId) {
        User user = commonService.findUserByIdIfExists(name);
        Todos todoList = user.getTodos();
        Todo todo = todoList.checkUserContainsTodo(todoId);
        return todoEntityToDto(todo);
    }

    public List<ResponseTodoDto> getUserTodoList(String name) {
        User user = commonService.findUserByIdIfExists(name);
        Todos todoList = user.getTodos();
        return todoListToTodoDtoList(todoList);
    }

    public ResponseTodoDto addTodo(String name, RequestTodoDto requestTodoDto) {
        User user = commonService.findUserByIdIfExists(name);
        Todo todo = new Todo(requestTodoDto.getTitle(), user);
        Todo savedTodo = todoRepository.save(todo);
        return todoEntityToDto(savedTodo);
    }


    public ResponseTodoDto updateTodoTitle(String name, Long todoId, String newTitle) {
        User user = commonService.findUserByIdIfExists(name);
        Todo todo = user.getTodos().checkUserContainsTodo(todoId);
        todo.updateTitle(newTitle);
        return todoEntityToDto(todo);
    }


    public void deleteTodo(String name, Long todoId) {
        User user = commonService.findUserByIdIfExists(name);
        Todo todo = user.getTodos().checkUserContainsTodo(todoId);
        todoRepository.delete(todo);
    }

    private ResponseTodoDto todoEntityToDto(Todo todo) {
        return new ResponseTodoDto(todo.getId(), todo.getTitle());
    }

    private List<ResponseTodoDto> todoListToTodoDtoList(Todos todos) {
        List<Todo> todoList = todos.getTodoList();
        List<ResponseTodoDto> output = new ArrayList<>();
        for (Todo todo : todoList) {
            output.add(todoEntityToDto(todo));
        }
        return output;
    }
}
