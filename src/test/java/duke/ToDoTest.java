package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void test() {
        ToDo t1 = new ToDo("play FIFA");
        t1.markDone();
        assertEquals(t1.toString(), "[T][X] play FIFA");
    }
}
