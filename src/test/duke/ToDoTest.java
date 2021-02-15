package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    public void testPrintTask() {
        assertEquals("[T][ ] 2103T iP", new ToDo("2103T iP").printTask());
    }
}