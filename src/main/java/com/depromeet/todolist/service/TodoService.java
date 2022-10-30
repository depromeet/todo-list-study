package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.TodoDto;
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

    public TodoDto.Response getTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        return todoEntityToDto(todo);
    }

    public List<TodoDto.Response> getUserTodos(String userId) {
        String validUserId = commonService.findUserByIdIfExists(userId).getUserId();
        List<Todo> allTodos = todoRepository.findByUserId(validUserId);
        return todoListToTodoDtoList(allTodos);
    }

    public TodoDto.Response addTodo(String userId, TodoDto.Request todoRequest) {
        User user = commonService.findUserByIdIfExists(userId);
        Todo savedTodo = todoRepository.save(new Todo(todoRequest.getTitle(), user.getUserId()));
        return todoEntityToDto(savedTodo);
    }

    public TodoDto.Response updateTodoTitle(String userId, Long todoId, String newTitle) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todo.updateTitle(newTitle);
        return todoEntityToDto(todo);
    }

    public void deleteTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todoRepository.delete(todo);
    }

    private TodoDto.Response todoEntityToDto(Todo todo) {
        return new TodoDto.Response(todo.getId(), todo.getTitle());
    }

    private List<TodoDto.Response> todoListToTodoDtoList(List<Todo> todos) {
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
