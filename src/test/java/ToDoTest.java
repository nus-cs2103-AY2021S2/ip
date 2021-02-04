import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createToDo_withDescription_stringReturned() {
        ToDo test = new ToDo("borrow book");
        assertEquals("T | 0 | borrow book",test.toString());
    }

    @Test
    public void createToDo_withNoDescription_stringReturned() {
        ToDo test = new ToDo("");
        assertEquals("T | 0 | ",test.toString());
    }
}
