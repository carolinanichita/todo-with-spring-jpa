package cn.todo.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.jayway.jsonpath.JsonPath;

import java.util.Map;

public class JsonPathAssertions {
    @Test
    void todoAssertions() {
        // given
        String todoString = "{\"id\":1," +
                "\"title\":\"Do Laundry\"," +
                "\"done\":true," +
                "\"dateCreated\":\"09/01/2022 03:20\"," +
                "\"dateDone\":\"07/04/2022 01:59\"," +
                "\"dueDate\":\"10/01/2022 04:00\"," +
                "\"lastUpdated\":\"07/04/2022 01:59\"," +
                "\"type\":null}";

        // when
        Map<String, String> todoMap = JsonPath.read(todoString, "$");

        // then
        Assertions.assertNotNull(todoMap);
        Assertions.assertInstanceOf(Map.class, todoMap);
        Assertions.assertEquals("Do Laundry", todoMap.get("title"));
        Assertions.assertEquals(true, todoMap.get("done"));
        Assertions.assertNull(todoMap.get("type"));
    }

    @Test
    void todoWithTodoTypeAssertions() {
        //given
        String todoStr = "{\"id\":134," +
                "\"title\":\"Do Laundry\"," +
                "\"done\":false," +
                "\"dateCreated\":\"09/01/2022 01:20\"," +
                "\"dateDone\":null," +
                "\"dueDate\":\"10/01/2022 02:00\"," +
                "\"lastUpdated\":null," +
                "\"type\":{\"description\":\"Todo for Personal Work\",\"code\":\"PERSONAL\"}}";

        //when
        Map<String, String> todoMap = JsonPath.read(todoStr, "$");
        Map<String, String> todoTypeMap = JsonPath.read(todoStr, "$.type");

        //then
        Assertions.assertNotNull(todoMap);
        Assertions.assertNotNull(todoTypeMap);
        Assertions.assertEquals("PERSONAL", todoTypeMap.get("code"));
    }
}
