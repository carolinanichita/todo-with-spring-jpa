package cn.todo.controllers;

import cn.todo.domains.TodoType;
import cn.todo.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todoType")
public class TodoTypeController {

    private TodoTypeService todoTypeService;

    @Autowired
    public TodoTypeController(TodoTypeService todoTypeService) {
        this.todoTypeService = todoTypeService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World! from Educative course 'REST APIs with Spring, JPA, and Springfox'";
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public TodoType create(@RequestBody @Valid TodoType todoType) {
        return todoTypeService.create(todoType);
    }

    @GetMapping(value = "/{code}", produces = {"application/json", "application/xml"})
    public ResponseEntity<TodoType> read(@PathVariable("code") String code) {
        TodoType todoType = todoTypeService.findByCode(code);
        if (null != todoType) {
            return new ResponseEntity(todoType, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public TodoType updateTodo(@RequestBody TodoType todoType) {
        return todoTypeService.update(todoType);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity delete(@PathVariable("code") String code) {
        try {
            todoTypeService.deleteByCode(code);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<TodoType> findAll(@RequestParam String sort, @RequestParam String order, @RequestParam int pageNumber, @RequestParam int numOfRecords) {
        return todoTypeService.findAll(sort, Sort.Direction.fromString(order), pageNumber, numOfRecords);
    }

}