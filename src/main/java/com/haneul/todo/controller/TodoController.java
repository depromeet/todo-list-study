package com.haneul.todo.controller;

import com.haneul.todo.controller.common.Response;
import com.haneul.todo.controller.common.TodoResourceAssembler;
import com.haneul.todo.dto.request.TodoRequest;
import com.haneul.todo.dto.response.TodoDeleteResponse;
import com.haneul.todo.dto.response.TodoListResponse;
import com.haneul.todo.dto.response.TodoResponse;
import com.haneul.todo.entity.Todo;
import com.haneul.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoResourceAssembler todoResourceAssembler;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<EntityModel<Todo>> createTodo(@RequestBody TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.createTodo(todoRequest);
        Todo todo = modelMapper.map(todoResponse, Todo.class);
        return Response.created(todoResourceAssembler.toModel(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Todo>> findTodo(@PathVariable Long id) {
        TodoResponse todoResponse = todoService.findById(id);
        Todo todo = modelMapper.map(todoResponse, Todo.class);
        return Response.ok(todoResourceAssembler.toModel(todo));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Todo>>> findTodos() {
        TodoListResponse todoResponses = todoService.findAll();
        List<Todo> todos = new ArrayList<>();

        todoResponses.todoResponses()
                .forEach(todoResponse ->
                                todos.add(modelMapper.map(todoResponse, Todo.class))
                );
        return Response.ok(todoResourceAssembler.toCollectionModel(todos));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Todo>> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.update(todoRequest, id);
        Todo todo = modelMapper.map(todoResponse, Todo.class);
        return Response.ok(todoResourceAssembler.toModel(todo));
    }

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<Todo>> updateStatus(@PathVariable Long id) {
        TodoResponse todoResponse = todoService.updateStatus(id);
        Todo todo = modelMapper.map(todoResponse, Todo.class);
        return Response.ok(todoResourceAssembler.toModel(todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDeleteResponse> deleteTodo(@PathVariable Long id) {
        TodoDeleteResponse todoDeleteResponse = todoService.deleteById(id);
        return Response.ok(todoDeleteResponse);
    }
}
