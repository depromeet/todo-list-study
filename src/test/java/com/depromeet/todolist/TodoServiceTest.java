package com.depromeet.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.depromeet.todolist.todo.application.TodoService;
import com.depromeet.todolist.todo.domain.Todo;
import com.depromeet.todolist.todo.infrastructure.TodoRepository;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    Todo todo1 = new Todo("first item", false);
    Todo todo2 = new Todo("todo two", true);
    Todo todo3 = new Todo("todo333", false);

    @Test
    public void create_todo_item() throws Exception {
        given(todoRepository.save(any())).willReturn(todo1);
        given(todoRepository.findById(any())).willReturn(Optional.ofNullable(todo1));

        Todo newTodo = todoService.createItem("second item", true);

        Todo findTodo = todoService.getItem(newTodo.getId());

        assertEquals(todo1, findTodo);
    }

    @Test
    public void get_todo_item() throws Exception {
        given(todoRepository.findById(any())).willReturn(Optional.ofNullable(todo1));

        Todo newTodo = todoService.getItem(new UUID(1, 2));

        Todo findTodo = todoService.getItem(newTodo.getId());

        assertEquals(todo1, findTodo);
    }

    @Test
    public void get_todo_list() throws Exception {
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(todo1);
        todoList.add(todo2);
        todoList.add(todo3);
        given(todoRepository.findAll()).willReturn(todoList);

        List<Todo> findTodoList = todoService.getList();

        assertEquals(todoList, findTodoList);
    }

    @Test
    public void update_todo_item() throws Exception {
        given(todoRepository.findById(any())).willReturn(Optional.ofNullable(todo1));

        Todo newTodo = todoService.updateItem(todo2.getId(), "second item", true);

        Todo findTodo = todoService.getItem(newTodo.getId());

        assertEquals(todo1, findTodo);
    }

    @Test
    public void delete_todo_item() throws Exception {

        given(todoRepository.findById(todo1.getId())).willReturn(Optional.ofNullable(todo2));

        todoService.deleteItem(todo1.getId());

        verify(todoRepository).delete(todo2);
    }
}
