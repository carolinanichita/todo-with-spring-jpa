package cn.todo.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.todo.services.TodoTypeService;
import cn.todo.domains.TodoType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoTypeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoTypeService todoTypeService;

    @BeforeEach
    void createTodoType() throws Exception {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDescription("Todo Type for Personal Work");

        mockMvc.perform(post("/api/todoType")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(personal)))
                .andExpect(status().isOk());
    }

    @Test
    void testTodoTypeCreateThroughAllLayers() throws Exception {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDescription("Todo Type for Personal Work");

        mockMvc.perform(post("/api/todoType")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(personal)))
                .andExpect(status().isOk());

        TodoType todoType = todoTypeService.findByCode("PERSONAL");
        Assertions.assertEquals(personal.getDescription(), todoType.getDescription());
    }

    @Test
    void testTodoTypeReadThroughAllLayers() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/todoType/PERSONAL")
                        .contentType("application/json"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        TodoType todoType = objectMapper.readValue(content, TodoType.class);

        Assertions.assertEquals("PERSONAL", todoType.getCode());
    }

    @Test
    void testTodoTypeUpdateThroughAllLayers() throws Exception {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDescription("Todo Type for Personal Work 123");

        mockMvc.perform(put("/api/todoType")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(personal)))
                .andExpect(status().isOk());

        TodoType todoType = todoTypeService.findByCode("PERSONAL");
        Assertions.assertEquals(personal.getDescription(), todoType.getDescription());
    }

    //@Test
    void testTodoTypeDeleteThroughAllLayers() throws Exception {
        mockMvc.perform(delete("/api/todoType/PERSONAL")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

}

