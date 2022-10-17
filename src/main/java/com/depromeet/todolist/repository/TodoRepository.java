package com.depromeet.todolist.repository;

import com.depromeet.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.userName = :username")
    List<Todo> findAllTodos(@Param("username") String username);
}
