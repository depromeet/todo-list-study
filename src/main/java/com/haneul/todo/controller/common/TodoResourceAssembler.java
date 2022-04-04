package com.haneul.todo.controller.common;

import com.haneul.todo.controller.TodoController;
import com.haneul.todo.entity.Todo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TodoResourceAssembler implements RepresentationModelAssembler<Todo, EntityModel<Todo>> {

    @Override
    public EntityModel<Todo> toModel(Todo todo) {
        return EntityModel.of(
                todo,
                linkTo(methodOn(TodoController.class).findTodo(todo.getId())).withSelfRel(),
                linkTo(methodOn(TodoController.class)).withRel("update"),
                linkTo(methodOn(TodoController.class)).withRel("delete")
        );
    }

    @Override
    public CollectionModel<EntityModel<Todo>> toCollectionModel(Iterable<? extends Todo> todos) {
        return RepresentationModelAssembler.super.toCollectionModel(todos)
                .add(linkTo(methodOn(TodoController.class).findTodos()).withSelfRel());
    }
}
