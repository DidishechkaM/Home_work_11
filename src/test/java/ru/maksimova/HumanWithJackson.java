package ru.maksimova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

public class HumanWithJackson {
    private final ClassLoader cl = HumanWithJackson.class.getClassLoader();
    @Test
    void jsonTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("human.json");
             InputStreamReader isr = new InputStreamReader(is)) {
            Human human = mapper.readValue(isr, Human.class);

            Assertions.assertEquals("Dima", human.name);
            Assertions.assertEquals("Football", human.hobbies.get(0));

        }
    }
}
