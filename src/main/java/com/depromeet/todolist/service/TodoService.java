package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Todo todo = checkUserHasTodo(name, todoId);
        return todoEntityToDto(todo);
    }


    public List<ResponseTodoDto> getUserTodoList(String name) {
        String userName = commonService.findUserByIdIfExists(name).getName();
        List<Todo> allTodos = todoRepository.findAllTodos(userName);
        return todoListToTodoDtoList(allTodos);
    }


    public ResponseTodoDto addTodo(String name, RequestTodoDto requestTodoDto) {
        String userName = commonService.findUserByIdIfExists(name).getName();
        Todo savedTodo = todoRepository.save(new Todo(requestTodoDto.getTitle(), userName));
        return todoEntityToDto(savedTodo);
    }


    public ResponseTodoDto updateTodoTitle(String name, Long todoId, String newTitle) {
        Todo todo = checkUserHasTodo(name, todoId);
        todo.updateTitle(newTitle);
        return todoEntityToDto(todo);
    }


    public void deleteTodo(String name, Long todoId) {
        Todo todo = checkUserHasTodo(name, todoId);
        todoRepository.delete(todo);
    }


    private ResponseTodoDto todoEntityToDto(Todo todo) {
        return new ResponseTodoDto(todo.getId(), todo.getTitle());
    }


    private List<ResponseTodoDto> todoListToTodoDtoList(List<Todo> todos) {
        return todos.stream().map(this::todoEntityToDto).collect(Collectors.toList());
    }


    private Todo checkUserHasTodo(String name, Long todoId) {
        Todo todo = checkTodoExists(todoId);
        User user = commonService.findUserByIdIfExists(name);
        if (!user.getName().equals(todo.getUserName())) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.NO_TODO)
                    .errorDetail(name + " 사용자에 존재하지 않는 할 일")
                    .build();
        }
        return todo;
    }


    private Todo checkTodoExists(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> BusinessException.builder()
                .errorCode(ErrorCode.NO_TODO)
                .build());
    }
}
