package justin;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

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