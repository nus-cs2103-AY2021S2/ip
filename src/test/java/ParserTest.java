import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @DisplayName("Test for list and all. ")
    @Test
    public void listTest() throws DukeException {
        ParserOutput out = Parser.parse("list");
        ParserOutput outAll = Parser.parse("all");
        assertEquals(out.getAction(), 5);
        assertAll("list and all", () ->
                assertEquals(out.getAction(), 5), () ->
                assertEquals(outAll.getAction(), 5)
        );
    }

    @DisplayName("Test for add. ")
    @Test
    public void addTest() throws DukeException {
        ParserOutput out = Parser.parse("todo do something");
        assertEquals(out.getAction(), 3);
    }

    @DisplayName("Test if parser creates Todo object when todo is added. ")
    @Test
    public void addTodoTest() throws DukeException {
        ParserOutput out = Parser.parse("todo do something");
        assertThat(out.getTask(), instanceOf(Todo.class));
    }

    @DisplayName("Test if parser creates Event object when todo is added. ")
    @Test
    public void addEventTest() throws DukeException {
        ParserOutput out = Parser.parse("event do something /at 2021-1-11");
        assertThat(out.getTask(), instanceOf(Event.class));
    }

    @DisplayName("Test if parser creates Deadline object when todo is added. ")
    @Test
    public void addDeadlineTest() throws DukeException {
        ParserOutput out = Parser.parse("deadline abc /by 2021-1-11");
        assertThat(out.getTask(), instanceOf(Deadline.class));
    }

    @DisplayName("Test for delete. ")
    @Test
    public void deleteTest() throws DukeException {
        ParserOutput out = Parser.parse("delete 1");
        assertEquals(out.getAction(), 1);
    }

    @DisplayName("Test for done. ")
    @Test
    public void doneTest() throws DukeException {
        ParserOutput out = Parser.parse("done 1");
        assertEquals(out.getAction(), 2);
    }

    @DisplayName("Test for bye. ")
    @Test
    public void byeTest() throws DukeException {
        ParserOutput out = Parser.parse("bye");
        assertEquals(out.isBye(), true);
    }

    @DisplayName("Test for pipe with list. ")
    @Test
    public void listPipe() throws DukeException {
        ParserOutput out = Parser.parse("list | done");
        assertAll("actions and nextActions", () ->
                    assertEquals(out.getAction(), 6), () ->
                    assertEquals(out.getNextAction(), 2), () ->
                    assertEquals(out.getPipeInput().getAction(), 5)
        );
    }
}
