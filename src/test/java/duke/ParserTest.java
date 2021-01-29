package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseDate() {
        assertEquals("2021-01-01", new Parser().parseDate("january 1 2021"));
        assertEquals("2020-02-02", new Parser().parseDate("february 2 2020"));
        assertEquals("2020-10-03", new Parser().parseDate("october 3 2020"));
    }

    @Test
    public void testParseAddToDo() {
        assertEquals("testing todo", new Parser().parseAddTodo("todo testing todo").getTaskDetail());
        assertEquals("i want to do this!", new Parser().parseAddTodo("todo i want to do this!").getTaskDetail());
        assertEquals("I WANT TO DO NOTHING", new Parser().parseAddTodo("todo I WANT TO DO NOTHING").getTaskDetail());
    }

    @Test
    public void testParseAddEvent() {
        assertEquals("testing event addition",
                new Parser().parseAddEvent("event testing event addition /at 2020-01-01").getTaskDetail());
        assertEquals("january 1 2020", new Parser().parseAddEvent("event test /at 2020-01-01").getEventDateString());
        assertEquals("testing event",
                new Parser().parseAddEvent("event testing event /at 2021-04-06").getTaskDetail());
    }

    @Test
    public void testParseAddDeadline() {
        assertEquals("testing deadline addition".length(),
                new Parser().parseAddDeadline("deadline testing deadline addition /by 2020-01-01").getTaskDetail().length());
        assertEquals("january 1 2020", new Parser().parseAddDeadline("deadline test /by 2020-01-01").getDeadline());
        assertEquals("testing deadline",
                new Parser().parseAddDeadline("deadline testing deadline /by 2021-04-06").getTaskDetail());
    }

}
