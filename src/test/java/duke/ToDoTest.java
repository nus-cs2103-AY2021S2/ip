package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void initialisationTest() {
        ToDo toDo = new ToDo("ip");
        assertEquals("[T][ ] ip", toDo.toString());
    }

    @Test
    public void markAsDoneTest() {
        ToDo toDo = new ToDo("ip");
        toDo.markAsDone();
        assertEquals("[T][X] ip", toDo.toString());
    }

    @Test
    public void getStatusIconTest() {
        ToDo toDo = new ToDo("ip");
        assertEquals(" ", toDo.getStatusIcon());
    }
}
