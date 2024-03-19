package cn.todo.services;

import cn.todo.domains.TodoType;
import cn.todo.repositories.TodoTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TodoTypeService {

    private TodoTypeRepository todoTypeRepository;
    private static Map<String, TodoType> todoTypeCollection = new HashMap<>();

    public TodoTypeService(TodoTypeRepository todoTypeRepository) {
        this.todoTypeRepository = todoTypeRepository;
    }
    public TodoType create (TodoType todoType) {
        todoTypeCollection.put(todoType.getCode(), todoType);
        return todoType;
    }

    public TodoType findByCode(String code) {
        return todoTypeCollection.get(code);
    }

    public TodoType update(TodoType todoType) {
        todoType.setLastUpdated(new Date());
        todoTypeCollection.put(todoType.getCode(), todoType);
        return todoType;
    }

    public void deleteByCode(String code) throws Exception {
        if (todoTypeCollection.remove(code) == null) {
            throw new Exception("TodoType doesn't exist");
        }
    }
}
