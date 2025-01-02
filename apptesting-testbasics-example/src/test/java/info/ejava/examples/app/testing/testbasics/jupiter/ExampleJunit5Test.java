package info.ejava.examples.app.testing.testbasics.jupiter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
@TestMethodOrder(MethodOrderer.Random.class)
class ExampleJunit5Test {

    @BeforeAll
    static void setUpClass() {
        log.info("setUpClass");
    }

    @BeforeEach
    void setUp() {
        log.info("setUp");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown");
    }

    @AfterAll
    static void tearDownClass() {
        log.info("tearDownClass");
    }

    @Test
    @Order(1)
    void two_plus_two() {
        log.info("2+2 = 4");
        assertEquals(4, 2 + 2);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("just demonstrationg expected exception");
        });
        assertTrue(ex.getMessage().startsWith("just demo"));
    }

    @Test
    @Order(2)
    void one_and_one() {
        log.info("1+1 = 2");
        assertTrue(1 + 1 == 2, "problem with 1+1");
        assertEquals(2, 1 + 1, () -> String.format("Problem with %d+%d , 1,1"));
    }

    @Test
    @Order(3)
    public void exceptions() {
        RuntimeException ex1 = Assertions.assertThrows(RuntimeException.class,
                () -> {
                    throw new IllegalArgumentException("example exception");
                });

    }
}
