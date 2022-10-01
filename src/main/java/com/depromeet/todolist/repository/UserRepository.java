package com.depromeet.todolist.repository;

import com.depromeet.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
