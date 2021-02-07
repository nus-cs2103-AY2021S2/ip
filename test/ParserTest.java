import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseDateTimeDeadline() {
        Parser parser = new Parser();
        String sample = "[D][ ]   return book   (by: 31/01/2021)";
        assertEquals("31/01/2021", parser.parseDateTimeDeadline(sample));
    }

    @Test
    void parseDateTimeEvent() {
        Parser parser = new Parser();
        String sample = "[E][✓]   project meeting   (at: 01/02/2021)";
        assertEquals("01/02/2021", parser.parseDateTimeEvent(sample));
    }

    @Test
    void parseTodoDescription() {
        Parser parser = new Parser();
        String sample = "[T][ ]   borrow book";
        assertEquals(" borrow book", parser.parseTodoDescription(sample));
    }

    @Test
    void parseDeadlineEventDescription() {
        Parser parser = new Parser();
        String sample = "[D][ ]   return book   (by: 31/01/2021)";
        assertEquals(" return book   ", parser.parseDeadlineEventDescription(sample));
    }
}