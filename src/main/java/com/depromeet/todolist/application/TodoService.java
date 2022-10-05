package com.depromeet.todolist.application;

import com.depromeet.todolist.domain.interfaces.ITodoRepository;
import com.depromeet.todolist.infrastructure.mysql.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final ITodoRepository todoRepository;
    public String findAll(){
        return this.todoRepository.findAll().toString();
    }

    public String findById(Long id){
        return this.todoRepository.findById(id).toString();
    }

    public String save(TodoEntity todoItem){
        TodoEntity result = this.todoRepository.save(todoItem);
        return result.toString();
    }
    public int remove(Long id){
        return this.todoRepository.delete(id);
    }

}
