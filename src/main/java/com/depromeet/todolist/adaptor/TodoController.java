package com.depromeet.todolist.adaptor;

import com.depromeet.todolist.application.TodoService;
import com.depromeet.todolist.infrastructure.mysql.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    @ResponseBody
    public String searchAll(){
        return this.todoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String searchById(@PathVariable("id") String id){
        return this.todoService.findById(Long.parseLong(id));
    }

    @PostMapping
    @ResponseBody
    public String createPost(@RequestParam String title,@RequestParam String content){

        TodoEntity post= new TodoEntity(null,title,content);
        this.todoService.save(post);
        return post.toString();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public int deletePost(@PathVariable("id") String id){
        return this.todoService.remove(Long.parseLong(id));
    }
}
