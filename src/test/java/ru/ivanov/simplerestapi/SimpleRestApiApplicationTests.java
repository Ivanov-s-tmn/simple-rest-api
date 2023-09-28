package ru.ivanov.simplerestapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleRestApiApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(SimpleRestApiApplication::new);
        Assertions.assertDoesNotThrow(() -> SimpleRestApiApplication.main(new String[]{}));
    }

}
