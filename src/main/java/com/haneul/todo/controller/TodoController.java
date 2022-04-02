package com.haneul.todo.controller;

import com.haneul.todo.controller.common.Response;
import com.haneul.todo.dto.request.TodoRequest;
import com.haneul.todo.dto.response.TodoDeleteResponse;
import com.haneul.todo.dto.response.TodoResponse;
import com.haneul.todo.service.TodoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/api/v1/posts", produces = MediaTypes.HAL_JSON_VALUE)
public record TodoController(TodoService todoService) {

    @PostMapping
    public ResponseEntity<EntityModel<TodoResponse>> createTodo(@RequestBody TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.createTodo(todoRequest);
        EntityModel<TodoResponse> model = EntityModel.of(
                todoResponse,
                linkTo(methodOn(TodoController.class).findTodo(todoResponse.id())).withSelfRel(),
                linkTo(methodOn(TodoController.class)).withRel("update"),
                linkTo(methodOn(TodoController.class)).withRel("delete")
        );
        return Response.created(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TodoResponse>> findTodo(@PathVariable Long id) {
        TodoResponse todoResponse = todoService.findById(id);
        EntityModel<TodoResponse> model = EntityModel.of(
                todoResponse,
                linkTo(methodOn(TodoController.class).findTodo(todoResponse.id())).withSelfRel(),
                linkTo(methodOn(TodoController.class)).withRel("update"),
                linkTo(methodOn(TodoController.class)).withRel("delete")
        );
        return Response.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TodoResponse>>> findTodos()
            throws IllegalAccessException {
        List<EntityModel<TodoResponse>> todoResponses = new ArrayList<>();
        todoService.findAll()
                .todoResponses()
                .forEach(todoResponse ->
                        todoResponses.add(EntityModel.of(
                                todoResponse,
                                linkTo(methodOn(TodoController.class).findTodo(todoResponse.id())).withSelfRel(),
                                linkTo(methodOn(TodoController.class)).withRel("update"),
                                linkTo(methodOn(TodoController.class)).withRel("delete")
                        ))
                );

        CollectionModel<EntityModel<TodoResponse>> model = CollectionModel.of(
                todoResponses,
                linkTo(methodOn(TodoController.class).findTodos()).withSelfRel()
        );
        return Response.ok(model);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<TodoResponse>> updateTodo(@PathVariable Long id, @RequestBody TodoRequest todoRequest) {
        TodoResponse todoResponse = todoService.update(todoRequest, id);

        EntityModel<TodoResponse> model = EntityModel.of(
                todoResponse,
                linkTo(methodOn(TodoController.class).findTodo(todoResponse.id())).withSelfRel(),
                linkTo(methodOn(TodoController.class)).withRel("update"),
                linkTo(methodOn(TodoController.class)).withRel("delete")
        );
        return Response.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDeleteResponse> deleteTodo(@PathVariable Long id) {
        TodoDeleteResponse todoDeleteResponse = todoService.deleteById(id);
        return Response.ok(todoDeleteResponse);
    }

}
