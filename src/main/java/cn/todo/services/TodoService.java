package cn.todo.services;

import cn.todo.domains.Todo;

import cn.todo.events.TodoCreationEvent;
import cn.todo.repositories.TodoRepository;
import cn.todo.repositories.TodoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoTypeRepository todoTypeRepository;
    private Validator validator;

    @Autowired
    public TodoService(TodoRepository todoRepository, TodoTypeRepository todoTypeRepository, Validator validator) {
        this.todoRepository = todoRepository;
        this.todoTypeRepository = todoTypeRepository;
        this.validator = validator;
    }

    @Transactional
    public Todo create(Todo todo) {
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        for (ConstraintViolation<Todo> violation : violations) {
            System.out.println(violation.getMessage());
        }
        if (violations.size() < 1) {
            todo.afterSave();
            todoRepository.save(todo);
        }
        return todo;
    }

    public Todo findById(Long id) {
        Optional<Todo> todoResult = todoRepository.findById(id);
        if (todoResult.isPresent()) {
            return todoResult.get();
        } else {
            return null;
        }
    }

    public Todo update(Todo todo) {
        todo.setLastUpdated(new Date());
        if (todo.isDone()) {
            todo.setDateDone(new Date());
        }
        todo = todoRepository.save(todo);
        return todo;
    }

    public void deleteById(Long id) throws Exception {
        if (!todoRepository.existsById(id)) {
            throw new Exception("Id doesn't exist");
        }
        todoRepository.deleteById(id);
    }

    public List<Todo> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords) {
        Sort idDesc = Sort.by(order, sort);
        Pageable pageRequest = PageRequest.of(pageNumber, numOfRecords, idDesc);
        Page<Todo> todoPages = todoRepository.findAll(pageRequest);
        List<Todo> todos = todoPages.getContent();
        return todos;
    }

    /*@EventListener
    public void handleTodoCreationEvent(TodoCreationEvent event) {
        System.out.println("Handle TodoCreationEvent");
    }*/

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTodoCreationEvent(TodoCreationEvent event) {
        System.out.println("Handle TodoCreationEvent");
    }

}