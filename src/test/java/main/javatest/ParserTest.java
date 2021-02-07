package main.javatest;

import duke.DukeException;
import duke.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parserEvent() {
        Parser parser = new Parser();
        String[] actual = {"event", "Dinner", "2019-12-11"};
        try {
            assertEquals(actual[0], parser.parseCommand("event Dinner /at 2019-12-11")[0]);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void parserDeadline() {
        Parser parser = new Parser();
        try {
            assertEquals(null, parser.parseCommand("deadline Dinner 2019-12-11"));
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeException("Invalid description of deadline").toString(), e.toString());
        }
    }

    @Test
    public void parserToDo() {
        Parser parser = new Parser();
        try {
            assertEquals(null, parser.parseCommand("todo"));
            fail();
        } catch (DukeException e) {
            assertEquals(new DukeException("Invalid input, description "
                    + "of todo cannot be empty").toString(), e.toString());
        }
    }
}
