package com.depromeet.todolist.service;

import com.depromeet.todolist.converter.TodoEntityConverter;
import com.depromeet.todolist.dto.TodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.exception.BusinessException;
import com.depromeet.todolist.exception.ErrorCode;
import com.depromeet.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final UserService userService;
    private final TodoRepository todoRepository;
    private final TodoEntityConverter todoEntityConverter;

    public TodoDto.Response getTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        return todoEntityConverter.todoEntityToDto(todo);
    }

    public List<TodoDto.Response> getUserTodos(String userId) {
        String validUserId = userService.findUserByIdIfExists(userId).getUserId();
        List<Todo> todos = todoRepository.findByUserId(validUserId);
        return todoEntityConverter.todosToTodosDto(todos);
    }

    public EntityModel<Todo> addTodo(String userId, TodoDto.Request todoRequest) {
        User user = userService.findUserByIdIfExists(userId);
        Todo savedTodo = todoRepository.save(new Todo(todoRequest.getTitle(), user.getUserId()));
        return todoEntityConverter.toModel(savedTodo);
    }

    public EntityModel<Todo> updateTodoTitle(String userId, Long todoId, String newTitle) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todo.updateTitle(newTitle);
        return todoEntityConverter.toModel(todo);
    }

    public void deleteTodo(String userId, Long todoId) {
        Todo todo = checkUserHasTodo(userId, todoId);
        todoRepository.delete(todo);
    }

    private Todo checkUserHasTodo(String userId, Long todoId) {
        Todo todo = checkTodoExists(todoId);
        User user = userService.findUserByIdIfExists(userId);
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
                .errorDetail("존재하지 않는 할 일")
                .build());
    }
}
