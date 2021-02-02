import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parserTest() throws Exception {
        assertEquals("borrow book",
                Parser.getTask("borrow book"));
    }

    @Test
    public void parser_task_success() throws Exception {
        assertEquals("project meeting",
                Parser.getTask("project meeting /at 2021-10-14"));
    }

    @Test
    public void parser_timeAt_success() throws Exception {
        assertEquals("Oct 14 2021",
                Parser.getTimeAt("project meeting /at 2021-10-14"));
    }

    @Test
    public void parser_timeBy_success() throws Exception {
        assertEquals("May 2 2021",
                Parser.getTimeBy("read book /by 2021-05-02"));
    }

    @Test
    public void stringTest() throws Exception {
        assertEquals("[T][ ] borrow book",
                new Todo("borrow book").toString());
    }

    @Test
    public void string_event_success() throws Exception {
        assertEquals("[E][ ] project meeting (at: Oct 14 2021)",
                new Event("project meeting", "Oct 14 2021").toString());
    }

    @Test
    public void string_deadline_success() throws Exception {
        assertEquals("[D][ ] read book (by: May 2 2021)",
                new Deadline("read book", "May 2 2021").toString());
    }

}
