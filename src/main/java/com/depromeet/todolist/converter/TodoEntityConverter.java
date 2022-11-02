package com.depromeet.todolist.converter;

import com.depromeet.todolist.controller.TodoListController;
import com.depromeet.todolist.dto.TodoDto;
import com.depromeet.todolist.entity.Todo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TodoEntityConverter implements RepresentationModelAssembler<Todo, EntityModel<Todo>> {

    @Override
    public EntityModel<Todo> toModel(Todo todo) {
        return EntityModel.of(todo,
                linkTo(methodOn(TodoListController.class).userTodo(todo.getUserId(), todo.getId())).withSelfRel(),
                linkTo(methodOn(TodoListController.class).userTodos(todo.getUserId())).withRel("todos"));
    }

    public TodoDto.Response todoEntityToDto(Todo todo) {
        return new TodoDto.Response(todo.getId(), todo.getTitle());
    }

    public List<TodoDto.Response> todosToTodosDto(List<Todo> todos) {
        return todos.stream().map(this::todoEntityToDto).collect(Collectors.toList());
    }
}
