import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

public class ToDoTest {
    @Test
    public void createToDo_withDescription_stringReturned() {
        ToDo test = new ToDo("borrow book");
        assertEquals("[T][✘] borrow book", test.toString());
    }

    @Test
    public void createToDo_withNoDescription_stringReturned() {
        ToDo test = new ToDo("");
        assertEquals("[T][✘] ", test.toString());
    }
}
