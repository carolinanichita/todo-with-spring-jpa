package cn.todo.services;

import cn.todo.domains.Todo;

import cn.todo.repositories.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.util.*;

@Service
public class TodoService {
    private Map<Long, Todo> todoCollection = new HashMap<>();
    private static long idCount = 1;

    private TodoRepository todoRepository;
    private Validator validator;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TodoService(TodoRepository todoRepository, Validator validator, ApplicationEventPublisher applicationEventPublisher) {
        this.todoRepository = todoRepository;
        this.validator = validator;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<Todo> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords) {
        Sort idDesc = Sort.by(order, sort);
        Pageable pageRequest = PageRequest.of(pageNumber, numOfRecords, idDesc);
        Page<Todo> todoPages = todoRepository.findAll(pageRequest);
        List<Todo> todos = todoPages.getContent();
        return todos;
    }

    @Transactional
    public Todo create(Todo todo) {
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
        for (ConstraintViolation<Todo> violation : violations) {
            System.out.println(violation.getMessage());
        }
        if (violations.size() < 1) {
            todoRepository.save(todo);
        }
        return todo;
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

    public Collection<Todo> readAllDone() {
        return todoRepository.findAllDone();
    }

    public List<Todo> readAllByTitle(String title) {
        return todoRepository.findAllTitle(title);
    }

    public Todo findById(Long id) {
        Optional<Todo> todoResult = todoRepository.findById(id);
        if (todoResult.isPresent()) {
            return todoResult.get();
        } else {
            return null;
        }
    }

    public List<Todo> findAllByDone() {
        //return (List<Todo>) todoRepository.findAllByDone(true, Sort.by(Sort.Direction.DESC, "id"));
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));

        return (List<Todo>) todoRepository.findAllByDone(true, firstPageWithTwoElements);
    }
}
