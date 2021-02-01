import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parserTest1() throws Exception {
        assertEquals("borrow book",
                Parser.getTask("borrow book"));
    }

    @Test
    public void parserTest2() throws Exception {
        assertEquals("project meeting",
                Parser.getTask("project meeting /at 2021-10-14"));
    }

    @Test
    public void parserTest3() throws Exception {
        assertEquals("Oct 14 2021",
                Parser.getTimeAt("project meeting /at 2021-10-14"));
    }

    @Test
    public void parserTest4() throws Exception {
        assertEquals("May 2 2021",
                Parser.getTimeBy("read book /by 2021-05-02"));
    }

    @Test
    public void stringTest1() throws Exception {
        assertEquals("[T][ ] borrow book",
                new Todo("borrow book").toString());
    }

    @Test
    public void stringTest2() throws Exception {
        assertEquals("[E][ ] project meeting (at: Oct 14 2021)",
                new Event("project meeting", "Oct 14 2021").toString());
    }

    @Test
    public void stringTest3() throws Exception {
        assertEquals("[D][ ] read book (by: May 2 2021)",
                new Deadline("read book", "May 2 2021").toString());
    }

}
