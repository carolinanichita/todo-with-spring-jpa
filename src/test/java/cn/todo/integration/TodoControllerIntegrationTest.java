package cn.todo.integration;

import cn.todo.controllers.TodoController;
import cn.todo.domains.Todo;
import cn.todo.services.TodoService;
import cn.todo.services.TodoTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

@WebMvcTest(TodoController.class)
public class TodoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TodoService todoService;
    @MockBean
    private TodoTypeService todoTypeService;

    @Test
    public void givenTodo_whenGetTodo_thenReturnJson() throws Exception {
        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDateCreated(new Date());

        given(todoService.findById(1L)).willReturn(doLaundry);

        mockMvc.perform(get("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(doLaundry.getTitle())));
    }
}
