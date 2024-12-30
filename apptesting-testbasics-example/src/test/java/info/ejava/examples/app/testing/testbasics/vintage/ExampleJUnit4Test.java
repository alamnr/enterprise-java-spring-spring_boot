package info.ejava.examples.app.testing.testbasics.vintage;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleJUnit4Test {

    @BeforeClass
    public static void setUpClass() {
        log.info("setUpClass");
    }

    @AfterClass
    public static void tearDownClass() {
        log.info("tearDownClass");
    }

    @Before
    public void setUp() {
        log.info("setUp");
    }

    @After
    public void tearDown() {
        log.info("tearDown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void two_plus_two() {
        log.info("2+2 = 4");
        assertEquals(4, 2 + 2);
        //throw new IllegalArgumentException("Just demonstrating an expected exception");
    }

    @Test
    public void one_and_one() {
        log.info("1+1 = 2");
        assertTrue("Problem with 1+1", 1 + 1 == 2);
        assertEquals(String.format("problem with %d+%d", 1, 1), 2, 1 + 1);
    }
}
