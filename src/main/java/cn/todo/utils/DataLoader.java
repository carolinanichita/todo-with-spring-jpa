package cn.todo.utils;

import cn.todo.domains.Todo;
import cn.todo.domains.TodoType;
import cn.todo.repositories.TodoRestRepository;
import cn.todo.repositories.TodoTypeRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements ApplicationRunner {

    private TodoRestRepository todoRepository;
    private TodoTypeRestRepository todoTypeRepository;

    @Autowired
    public DataLoader(TodoRestRepository todoRepository, TodoTypeRestRepository todoTypeRepository) {
        this.todoRepository = todoRepository;
        this.todoTypeRepository = todoTypeRepository;
    }

    public void run(ApplicationArguments args) throws ParseException {
        TodoType personalType = new TodoType();
        personalType.setCode("PERSONAL");
        personalType.setDescription("Todo for Personal Work");
        todoTypeRepository.save(personalType);

        Todo todo1 = new Todo();
        todo1.setTitle("Do Laundry");
        todo1.setDescription("laundry...");
        todo1.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("09/01/2022 15:20"));
        todo1.setDueDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/01/2022 16:00"));
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("Pay electricity bill");
        todo2.setDescription("Pay electricity bill...");
        todo2.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/01/2022 15:20"));
        todo2.setDueDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/01/2022 16:00"));
        todoRepository.save(todo2);
    }
}