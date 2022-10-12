package com.depromeet.todolist.repository;

import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
