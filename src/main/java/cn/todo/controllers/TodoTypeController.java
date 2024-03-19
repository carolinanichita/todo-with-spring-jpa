//package cn.todo.controllers;
//
//import cn.todo.domains.TodoType;
//import cn.todo.services.TodoService;
//import cn.todo.services.TodoTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/todoType")
//public class TodoTypeController {
//
//    private TodoService todoService;
//    private TodoTypeService todoTypeService;
//    @Autowired
//    public TodoTypeController(TodoService todoService, TodoTypeService todoTypeService) {
//        this.todoService = todoService;
//        this.todoTypeService = todoTypeService;
//    }
//    @GetMapping("/hello")
//    public String helloWorld() {
//        return "Hello world! from Carolina Nichita";
//    }
//
//    @PostMapping(value = "/create", produces = {"application/json", "application/xml"})
//    public TodoType createTodoType() {
//        TodoType todoType = new TodoType();
//        todoType.setCode("PROFESSIONAL");
//        todoType.setDescription("Todo for Professional Work");
//        return todoType;
//    }
//
//    @PostMapping(consumes={"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"})
//    public TodoType create(@RequestBody TodoType todoType) {
//        return todoTypeService.create(todoType);
//    }
//
//    @GetMapping(value = "/read", produces = {"application/json", "application/xml"})
//    public TodoType readTodoType() {
//        TodoType todoType = new TodoType();
//        todoType.setDescription("Personal");
//        todoType.setDescription("Todo for Personal Work");
//        return todoType;
//    }
//
//    @GetMapping(value = "/{code}", produces = {"application/xml"})
//    public TodoType findByCode(@PathVariable("code") String code) {
//        return todoTypeService.findByCode(code);
//    }
//
//    @PutMapping
//    public TodoType update(@RequestBody TodoType todoType) {
//        return todoTypeService.update(todoType);
//    }
//
//    @DeleteMapping("/{code}")
//    public ResponseEntity deleteByCode(@PathVariable("code") String code) {
//        try {
//            todoTypeService.deleteByCode(code);
//            return new ResponseEntity(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
