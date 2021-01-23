package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test() {
        ToDo t1 = new ToDo("play FIFA");
        t1.markDone();
        assertEquals(t1.toString(), "[T][X] play FIFA");
    }
}
