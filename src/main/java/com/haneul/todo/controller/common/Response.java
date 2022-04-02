package com.haneul.todo.controller.common;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.nio.charset.StandardCharsets;

@Getter
public class Response<T> {

    public static <T> ResponseEntity<EntityModel<T>> created(EntityModel<T> entityModel) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
                .body(entityModel);
    }

    public static <T> ResponseEntity<EntityModel<T>> ok(EntityModel<T> entityModel) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
                .body(entityModel);
    }

    public static <T> ResponseEntity<CollectionModel<EntityModel<T>>> ok(CollectionModel<EntityModel<T>> collectionModel) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
                .body(collectionModel);
    }

    public static <T> ResponseEntity<T> ok(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(new MediaType("application", "json", StandardCharsets.UTF_8))
                .body(data);
    }
}
