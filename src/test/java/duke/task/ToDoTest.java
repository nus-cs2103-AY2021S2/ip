package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    void testFileStringConversion() {
        ToDo testToDo1 = new ToDo("todoTest1");
        ToDo testToDo2 = new ToDo("todoTest2");

        assertEquals("T|0|1|todoTest1", testToDo1.toFileString());
        assertEquals("T|0|1|todoTest2", testToDo2.toFileString());

        testToDo1.setDone();
        assertEquals("T|1|1|todoTest1", testToDo1.toFileString());

        testToDo2.setPriority(Priority.valueOf(0));
        assertEquals("T|0|0|todoTest2", testToDo2.toFileString());
    }

    @Test
    void testStringConversion() {
        ToDo testToDo1 = new ToDo("todoTest1");
        ToDo testToDo2 = new ToDo("todoTest2");

        assertEquals("[T][ ][!!]todoTest1", testToDo1.toString());
        assertEquals("[T][ ][!!]todoTest2", testToDo2.toString());

        testToDo1.setDone();
        assertEquals("[T][X][!!]todoTest1", testToDo1.toString());

        testToDo2.setPriority(Priority.valueOf(0));
        assertEquals("[T][ ][!]todoTest2", testToDo2.toString());
    }
}
