package cn.todo.unit;

import cn.todo.domains.TodoType;
import cn.todo.repositories.TodoTypeRepository;
import cn.todo.services.TodoTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;

public class TodoTypeServiceTest {
    private TodoTypeRepository todoTypeRepository = Mockito.mock(TodoTypeRepository.class);
    private Validator validator = Mockito.mock(Validator.class);
    private TodoTypeService todoTypeService = new TodoTypeService(todoTypeRepository, validator);

    @Test
    public void whenReadTodoType_thenReturnTodoType() {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());
        Optional<TodoType> personalOptional = Optional.ofNullable(personal);

        // given
        Mockito.when(todoTypeRepository.findById("PERSONAL")).thenReturn(personalOptional);

        // when
        TodoType result = todoTypeService.findByCode("PERSONAL");

        // then
        Assertions.assertEquals(personal.getCode(), result.getCode());
    }

}
