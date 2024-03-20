//package cn.todo.services;
//
//import cn.todo.domains.TodoType;
//import cn.todo.repositories.TodoTypeRestRepository;
//import org.springframework.stereotype.Service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//import java.util.*;
//
//@Service
//public class TodoTypeService {
//    private static Map<String, TodoType> todoTypeCollection = new HashMap<>();
//    private TodoTypeRestRepository todoTypeRestRepository;
//    private Validator validator;
//
//    @Autowired
//    public TodoTypeService(TodoTypeRestRepository todoTypeRestRepository, Validator validator) {
//        this.todoTypeRestRepository = todoTypeRestRepository;
//        this.validator = validator;
//    }
//
//    public TodoType create(TodoType todoType) {
//        Set<ConstraintViolation<TodoType>> violations = validator.validate(todoType);
//        for (ConstraintViolation<TodoType> violation : violations) {
//            System.out.println(violation.getMessage());
//        }
//        if (violations.size() < 1) {
//            todoTypeRestRepository.save(todoType);
//        }
//        return todoType;
//    }
//
//    public TodoType update(TodoType todoType) {
//        todoType.setLastUpdated(new Date());
//        todoType = todoTypeRestRepository.save(todoType);
//        return todoType;
//    }
//
//    public void deleteByCode(String code) throws Exception {
//        if (!todoTypeRestRepository.existsById(code)) {
//            throw new Exception("Code doesn't exist");
//        }
//        todoTypeRestRepository.deleteById(code);
//    }
//
//    public TodoType findByCode(String code) {
//        Optional<TodoType> todoTypeResult = todoTypeRestRepository.findById(code);
//        if (todoTypeResult.isPresent()) {
//            return todoTypeResult.get();
//        } else {
//            return null;
//        }
//    }
//
//    public List<TodoType> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords) {
//        Sort idDesc = Sort.by(order, sort);
//        Pageable pageRequest = PageRequest.of(pageNumber, numOfRecords, idDesc);
//        Page<TodoType> todoTypePages = todoTypeRestRepository.findAll();
//        List<TodoType> todoTypes = todoTypePages.getContent();
//        return todoTypes;
//    }
//}
