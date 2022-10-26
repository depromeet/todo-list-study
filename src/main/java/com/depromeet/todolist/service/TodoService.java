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


    public ResponseTodoDto getTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        return todoEntityToDto(todo);
    }


    public List<ResponseTodoDto> getUserTodoList(String userId) {
        String validUserId = commonService.findUserByIdIfExists(userId).getUserId();
        List<Todo> allTodos = todoRepository.findByUserId(validUserId);
        return todoListToTodoDtoList(allTodos);
    }


    public ResponseTodoDto addTodo(String userId, RequestTodoDto requestTodoDto) {
        User user = commonService.findUserByIdIfExists(userId);
        Todo savedTodo = todoRepository.save(new Todo(requestTodoDto.getTitle(), user.getUserId()));
        return todoEntityToDto(savedTodo);
    }


    public ResponseTodoDto updateTodoTitle(String userId, Long todoId, String newTitle) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todo.updateTitle(newTitle);
        return todoEntityToDto(todo);
    }


    public void deleteTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todoRepository.delete(todo);
    }


    private ResponseTodoDto todoEntityToDto(Todo todo) {
        return new ResponseTodoDto(todo.getId(), todo.getTitle());
    }


    private List<ResponseTodoDto> todoListToTodoDtoList(List<Todo> todos) {
        return todos.stream().map(this::todoEntityToDto).collect(Collectors.toList());
    }


    private Todo checkUserHasTodo(String userId, Long todoId) {
        Todo todo = checkTodoExists(todoId);
        User user = commonService.findUserByIdIfExists(userId);
        if (!user.getUserId().equals(todo.getUserId())) {
            throw BusinessException.builder()
                    .errorCode(ErrorCode.NO_TODO)
                    .errorDetail(user.getName() + " 사용자에 존재하지 않는 할 일")
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
