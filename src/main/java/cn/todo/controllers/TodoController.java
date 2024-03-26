package cn.todo.controllers;

import cn.todo.domains.Todo;
import cn.todo.services.TodoService;
import cn.todo.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private TodoService todoService;
    private TodoTypeService todoTypeService;

    @Autowired
    public TodoController(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    @PostMapping
    public Todo create(@Valid @RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> read(@PathVariable("id") Long id) {
        Todo todo = todoService.findById(id);
        if (null != todo) {
            return new ResponseEntity(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        try {
            todoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<Todo> findAll(@RequestParam String sort, @RequestParam String order, @RequestParam int pageNumber, @RequestParam int numOfRecords) {
        return todoService.findAll(sort, Sort.Direction.fromString(order), pageNumber, numOfRecords);
    }

}
