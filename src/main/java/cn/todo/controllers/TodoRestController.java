package cn.todo.controllers;

import cn.todo.domains.TodoType;
import cn.todo.repositories.TodoRestRepository;
import cn.todo.repositories.TodoTypeRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class TodoRestController {
    private TodoRestRepository todoRestRepository;
    private TodoTypeRestRepository todoTypeRestRepository;

    @Autowired
    public TodoRestController(TodoRestRepository todoRestRepository, TodoTypeRestRepository todoTypeRestRepository) {
       this.todoRestRepository = todoRestRepository;
       this.todoTypeRestRepository = todoTypeRestRepository;
    }

    @GetMapping(value = "/todo/todoTypes")
    public @ResponseBody ResponseEntity<?> todoTypes() {
        CollectionModel<TodoType> resources = CollectionModel.of(todoTypeRestRepository.findAll());
        resources.add(linkTo(methodOn(TodoRestController.class).todoTypes()).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
