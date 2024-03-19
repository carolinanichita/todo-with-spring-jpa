package cn.todo.controllers;

import cn.todo.domains.Todo;
import cn.todo.services.TodoService;
import cn.todo.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    TodoService todoService;
    TodoTypeService todoTypeService;

    @Autowired
    public TodoController(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    public Todo findById(@PathVariable("id") Long id) {
        return todoService.findById(id);
    }

    @PutMapping
    public Todo update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            todoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
