package cn.todo.integration;

import cn.todo.domains.Todo;
import cn.todo.domains.TodoType;
import cn.todo.repositories.TodoRepository;
import cn.todo.repositories.TodoTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import reactor.test.StepVerifier;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class TodoRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoTypeRepository todoTypeRepository;

    @Test
    public void whenTodoObjIsSaved_thenTodoObjIsPersisted() {
        // given
        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        todoRepository.save(doLaundry);
        Assertions.assertEquals(todoRepository.findById(doLaundry.getId()).get(), doLaundry);
    }

    @Test
    public void givenTodoObjIsPersisted_whenFindByTitle_thenReturnTodoObj() {
        // given
        Todo doLaundry = new Todo();
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        testEntityManager.persist(doLaundry);
        testEntityManager.flush();

        // when
        Todo found = todoRepository.findByTitle(doLaundry.getTitle());

        // then
        Assertions.assertEquals(found.getTitle(), doLaundry.getTitle());
    }

    @Test
    public void whenFindAllByTitle_thenReturnAllTodos() {
        // given
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());

        testEntityManager.persist(personal);

        Todo doLaundry = new Todo();
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setType(personal);

        Todo[] todos = new Todo[1];
        todos[0] = doLaundry;

        testEntityManager.persist(doLaundry);
        testEntityManager.flush();

        // when
        List<Todo> found = todoRepository.findAllByType(personal);

        // then
        Assertions.assertArrayEquals(found.toArray(), todos);
    }
}
