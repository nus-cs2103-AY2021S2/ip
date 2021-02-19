package justin;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class performs JUnit tests on Task.java
 * to ensure that input and output are of
 * expected values
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class TaskTest {


    @Test
    public void testTaskGetDescription() {

        Task t = new Task("testing task");

        assertEquals("testing task", t.getDescription());

    }

    @Test
    public void testTaskMarkAsDone() {

        Task t = new Task("testing task");
        t.markAsDone();

        assertEquals("[✓] testing task", t.toString());

    }

}