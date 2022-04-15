import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {

    @Test
    public void testSetDone() {
        ToDo testToDo = new ToDo("");
        testToDo.setDone(true);
        assertEquals(true, testToDo.getDone());
        testToDo.setDone(false);
        assertEquals(false, testToDo.getDone());
    }

    @Test
    public void testGetStatus() {
        ToDo testToDo = new ToDo("");
        testToDo.setDone(true);
        assertEquals("[T][X] ", testToDo.getStatus());
        testToDo.setDone(false);
        assertEquals("[T][ ] ", testToDo.getStatus());
    }

    @Test
    public void testSaveStatus() {
        ToDo testToDo = new ToDo("");
        testToDo.setDone(true);
        assertEquals("T | 1 | \n", testToDo.currentStatus());
        testToDo.setDone(false);
        assertEquals("T | 0 | \n", testToDo.currentStatus());
    }

    @Test
    public void testDoesDescriptionContain() {
        ToDo testToDo = new ToDo("");
        assertEquals(true, testToDo.doesDescriptionContain(""));
        assertEquals(false, testToDo.doesDescriptionContain("123"));
    }
}
