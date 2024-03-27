package cn.todo.unit;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AssertJAssertions {

    @Test
    void stringAssertion() {
        assertThat("REST APIs with Spring, JPA, and Springfox").isNotNull()
                .startsWith("REST")
                .containsIgnoringCase("jpa")
                .endsWith("Springfox");
    }

    @Test
    void assertionWithDescriptionMessage() {
        //given
        int age = 32;

        //when-then
        assertThat(age).as("check the age").isEqualTo(32);
    }

    @Test
    void assertionForList() {
        //given
        List<Integer> marks = new ArrayList<>();
        marks.add(80);
        marks.add(70);
        marks.add(65);
        marks.add(75);
        marks.add(95);

        //when-then
        assertThat(marks).hasSize(5);
        assertThat(80).isIn(marks);
        assertThat(marks).matches(m -> m.size() == 5);
    }

    @Test
    void assertionForMap() {
        //given
        Map<Integer, String> customerInfo = new HashMap<>();
        customerInfo.put(1, "Anshul");
        customerInfo.put(2, "Ankit");
        customerInfo.put(3, "Mike");
        customerInfo.put(4, "Salman");

        //when-then
        assertThat(customerInfo).containsEntry(2, "Ankit");
        assertThat(customerInfo).doesNotContainKey(6);
        assertThat(customerInfo).containsValues("Salman", "Anshul");
        assertThat(customerInfo.keySet()).containsExactly(1, 2, 3, 4);
    }

    @Test
    void assertionForExceptions() {
        //given
        List<Integer> marks = new ArrayList<>();
        marks.add(80);
        marks.add(70);
        marks.add(65);
        marks.add(75);
        marks.add(95);

        //when-then
        assertThatThrownBy(() -> marks.get(6)).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Index: 6, Size: 5");

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> marks.get(6))
                .withMessage("Index: 6, Size: 5");

        assertThatNoException().isThrownBy(() -> marks.get(4));
    }

    @Test
    void assertionForLocalDateTime() {
        //given
        LocalDateTime now = LocalDateTime.now();

        //when-then
        assertThat(now).isBefore(LocalDateTime.now());
        assertThat(now).isInstanceOf(LocalDateTime.class);
        assertThat(now).isStrictlyBetween(now.minusSeconds(10), now.plusSeconds(100));
        assertThat(now).isBetween(now.minusDays(1), now.plusSeconds(100));
    }
}

