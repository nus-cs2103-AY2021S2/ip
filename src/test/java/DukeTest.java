import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parserTest() {
        assertEquals("borrow book",
                Parser.getTask("borrow book"));
    }

    @Test
    public void parser_task_success() {
        assertEquals("project meeting",
                Parser.getTask("project meeting /at 2021-10-14"));
    }

    @Test
    public void parser_timeAt_success() {
        assertEquals("2021-10-14",
                Parser.getTimeAt("project meeting /at 2021-10-14"));
    }

    @Test
    public void parser_timeBy_success() {
        assertEquals("2021-05-02",
                Parser.getTimeBy("read book /by 2021-05-02"));
    }

    @Test
    public void stringTest() {
        assertEquals("[T][ ] borrow book",
                new Todo("borrow book").toString());
    }

    @Test
    public void string_event_success() {
        assertEquals("[E][ ] project meeting (at: Oct 14 2021)",
                new Event("project meeting", "Oct 14 2021").toString());
    }

    @Test
    public void string_deadline_success() {
        assertEquals("[D][ ] read book (by: May 2 2021)",
                new Deadline("read book", "May 2 2021").toString());
    }

    @Test
    public void ui_done_success() {
        Ui ui = new Ui();
        ui.showDone(new Todo("borrow book"));
        assertEquals("Nice! I've marked this task as done:\n[T][ ] borrow book",
                ui.getMessage());
    }

    @Test
    public void ui_list_success() {
        Ui ui = new Ui();
        ui.showList("1.[T][ ] borrow book");
        assertEquals("Here are the tasks in your list:\n1.[T][ ] borrow book",
                ui.getMessage());
    }

    @Test
    public void ui_add_success() {
        Ui ui = new Ui();
        ui.showAddMessage(new Todo("borrow book"), 5);
        assertEquals("Got it. I've added this task:\n"
                        + "[T][ ] borrow book\nNow you have "
                        + "5 tasks in the list.",
                ui.getMessage());
    }

    @Test
    public void ui_error_success() {
        Ui ui = new Ui();
        ui.showError(new DukeException("error"));
        assertEquals("error", ui.getMessage());
    }

    @Test
    public void ui_delete_success() {
        Ui ui = new Ui();
        ui.showDelete(new Todo("borrow book"), 5);
        assertEquals("Noted. I've removed this task:\n"
                        + "[T][ ] borrow book\nNow you have "
                        + "5 tasks in the list.",
                ui.getMessage());
    }

    @Test
    public void ui_find_success() {
        Ui ui = new Ui();
        ui.showFind("1.[T][ ] borrow book");
        assertEquals("Here are the matching tasks in your list:\n1.[T][ ] borrow book",
                ui.getMessage());
    }

    @Test
    public void ui_find_error() {
        Ui ui = new Ui();
        ui.showFind("");
        assertEquals("There are no matching task in your list",
                ui.getMessage());
    }

    @Test
    public void ui_date_success() {
        Ui ui = new Ui();
        ui.showDate("Oct 14 2021", "1.[E][ ] project meeting (at: Oct 14 2021)");
        assertEquals("Here are the tasks occurring on "
                        + "Oct 14 2021 in your list:\n"
                        + "1.[E][ ] project meeting (at: Oct 14 2021)",
                ui.getMessage());
    }

    @Test
    public void ui_date_error() {
        Ui ui = new Ui();
        ui.showDate("Oct 14 2021", "");
        assertEquals("There are no matching task in your list",
                ui.getMessage());
    }

}
