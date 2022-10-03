package com.depromeet.todolist.service;

import com.depromeet.todolist.dto.request.RequestTodoDto;
import com.depromeet.todolist.dto.response.ResponseTodoDto;
import com.depromeet.todolist.entity.Todo;
import com.depromeet.todolist.entity.User;
import com.depromeet.todolist.repository.TodoRepository;
import com.depromeet.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    //전체 조회
    public List<ResponseTodoDto> findAllTodo(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<ResponseTodoDto> responseTodoDtoList = new ArrayList<>();
            for (Todo todo : user.getTodoList()) {
                responseTodoDtoList.add(new ResponseTodoDto(todo.getTitle()));
            }
            return responseTodoDtoList;
        } else {
            log.info("[findAllTodo]사용자가 존재하지 않음");
            return new ArrayList<>();
        }
    }

    //단일 조회
    public ResponseTodoDto findTodo(Long userid, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        return new ResponseTodoDto(todo.getTitle());
    }

    // 할 일 추가
    public Todo addTodo(Long userId, RequestTodoDto requestTodoDto) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Todo todo = new Todo();
            todo.setTitle(requestTodoDto.getTitle());
            todo.setUser(user);
            log.info(user.getName() + "의 Todo : " + todo.getTitle() + " 추가 완료");
            return todoRepository.save(todo);
        } else {
            log.info("[addTodo]사용자가 존재하지 않음");
            return null;
        }
    }

    // 할 일 수정
    public void updateTodoTitle(Long todoId, String newTitle) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo != null) {
            todo.setTitle(newTitle);
            log.info("할 일 수정 성공");
        } else {
            log.info("[updateTodoTitle]할 일이 존재하지 않음");
        }
    }


    // 할 일 삭제
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
            log.info("할 일 삭제 성공");
        } else {
            log.info("[deleteTodo]할 일이 존재하지 않음");
        }

    }
}