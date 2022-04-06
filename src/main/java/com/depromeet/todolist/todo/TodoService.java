package com.depromeet.todolist.todo;

import com.depromeet.todolist.todo.dto.TodoRequest;
import com.depromeet.todolist.todo.dto.TodoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<List<TodoResponse>> findAll() {

        List<TodoResponse> todoResponses = todoRepository.findAll()
                .stream()
                .map(todo -> TodoResponse.of(todo))
                .collect(Collectors.toList());

        return Optional.of(todoResponses);
    }

    // COMMENT: @Transactional(readOnly = true)를 생략해도 되는지
    public Optional<TodoResponse> find(Long todoId) {
        return Optional.of(TodoResponse.of(findById(todoId)));
    }

    @Transactional(readOnly = true)
    public Todo findById(Long todoId) {

        return todoRepository.findById(todoId)
                .orElseThrow(() -> {
                    log.debug("There is no Todo(id) : {}", todoId);
                    return new IllegalArgumentException();
                });
    }

    @Transactional
    public void deleteById(Long todoId) {
        findById(todoId).delete();
    }

    @Transactional
    public void updateById(Long todoId, TodoRequest todoRequest) {
        findById(todoId).update(todoRequest);
    }

    // COMMENT: 메서드 depth(괄호 갯수)에 대한 고민
    @Transactional
    public Optional<TodoResponse> save(TodoRequest todoRequest) {
        return Optional.of(
                TodoResponse.of(
                        todoRepository.save(Todo.of(todoRequest.getContent(), todoRequest.getType()))));
    }
}
