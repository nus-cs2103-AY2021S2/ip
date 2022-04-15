import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createNewTodoTask() {
        Task task = new Todo("read book", false);
        assertEquals("[T][ ] read book", task.toString());
    }

    @Test
    public void createNewDeadlineTask() {
        Task task = new Deadline("return book", "2020-02-04", false, false);
        assertEquals("[D][ ] return book (by: Feb 4 2020)", task.toString());
    }

    @Test
    public void createNewEventTask() {
        Task task = new Event("project meeting", "2020-02-05", false, false);
        assertEquals("[E][ ] project meeting (at: Feb 5 2020)", task.toString());
    }
}
