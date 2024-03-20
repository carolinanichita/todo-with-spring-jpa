//package cn.todo.services;
//
//import cn.todo.domains.Todo;
//import cn.todo.events.TodoCreationEvent;
//
//import cn.todo.repositories.TodoRestRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;
//
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import java.util.*;
//
//@Service
//public class TodoService {
//    private static Map<Long, Todo> todoCollection = new HashMap<>();
//    private static long idCount = 1;
//
//    private TodoRestRepository todoRestRepository;
//    private Validator validator;
//    private ApplicationEventPublisher eventPublisher;
//
//    @Autowired
//    public TodoService(TodoRestRepository todoRestRepository, Validator validator, ApplicationEventPublisher eventPublisher) {
//        this.todoRestRepository = todoRestRepository;
//        this.validator = validator;
//        this.eventPublisher = eventPublisher;
//    }
//
//    public List<Todo> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords) {
//        Sort idDesc = Sort.by(order, sort);
//        Pageable pageRequest = PageRequest.of(pageNumber, numOfRecords, idDesc);
//        Page<Todo> todoPages = todoRestRepository.findAll(pageRequest);
//        List<Todo> todos = todoPages.getContent();
//        return todos;
//    }
//
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void handleTodoCreationEvent(TodoCreationEvent event) {
//        System.out.println("Handled TodoCreationEvent...");
//    }
//    @Transactional
//    public Todo create(Todo todo) {
//        Set<ConstraintViolation<Todo>> violations = validator.validate(todo);
//        for (ConstraintViolation<Todo> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//        if (violations.size() < 1) {
//            todo.afterSave();
//            todoRestRepository.save(todo);
//        } else {
//            return null;
//        }
//        return todo;
//    }
//
//    public Todo update(Todo todo) {
//        todo.setLastUpdated(new Date());
//        if (todo.isDone()) {
//            todo.setDateDone(new Date());
//        }
//        todo = todoRestRepository.save(todo);
//        return todo;
//    }
//
//    public void deleteById(Long id) throws Exception {
//        if (!todoRestRepository.existsById(id)) {
//            throw new Exception("Id doesn't exist");
//        }
//        todoRestRepository.deleteById(id);
//    }
//
//    public Collection<Todo> readAllDone() {
//        return todoRestRepository.findAllDone();
//    }
//
//    public List<Todo> readAllByTitle(String title) {
//        return todoRestRepository.findAllTitle(title);
//    }
//
//    public Todo findById(Long id) {
//        Optional<Todo> todoResult = todoRestRepository.findById(id);
//        if (todoResult.isPresent()) {
//            return todoResult.get();
//        } else {
//            return null;
//        }
//    }
//
//    public List<Todo> findAllByDone() {
//        //return (List<Todo>) todoRepository.findAllByDone(true, Sort.by(Sort.Direction.DESC, "id"));
//        Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "id"));
//
//        return (List<Todo>) todoRestRepository.findAllByDone(true, firstPageWithTwoElements);
//    }
//}
