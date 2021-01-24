import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private class DummyTaskList extends TaskList {
        DummyTaskList() {
            super();
        }
    }

    @Test
    public void parseCommand_Bye() throws DukeException {
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("bye", new TaskList()).toString());
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("BYE", new TaskList()).toString());
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("bYe", new TaskList()).toString());
    }

    @Test
    public void parseCommand_List() throws DukeException {
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("list", new TaskList()).toString());
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("LIST", new TaskList()).toString());
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("LisT", new TaskList()).toString());
    }

    @Test
    public void parseCommand_Deadline() throws DukeException {
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("deadline ", new TaskList()).toString());
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("DEADLINE ", new TaskList()).toString());
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("DeadLINe ", new TaskList()).toString());
    }

    @Test
    public void parseCommand_Event() throws DukeException {
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("event ", new TaskList()).toString());
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("EVENT ", new TaskList()).toString());
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("EvenT ", new TaskList()).toString());
    }

    @Test
    public void parseCommand_Todo() throws DukeException {
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("todo ", new TaskList()).toString());
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("TODO ", new TaskList()).toString());
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("TodO ", new TaskList()).toString());
    }

    @Test
    public void parseCommand_Delete() throws DukeException {
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("delete ", new TaskList()).toString());
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("DELETE ", new TaskList()).toString());
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("dElEte ", new TaskList()).toString());
    }

    @Test
    public void parseCommand_Done() throws DukeException {
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("done ", new TaskList()).toString());
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("DONE ", new TaskList()).toString());
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("DoNe ", new TaskList()).toString());
    }

    @Test
    public void parseCommand_invalidCommandOneLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("a", new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandZeroLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("", new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandLongLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("frhyuighrdiughiudfhgvijverhfiueeidfhaijfjiorejgiorshjglrkehgjklergaa" +
                                    "ghdfjkhvehrghiuyerhgidfbvijergfvnjrkdbgnvjkdfbnvjkfdnvjkdfbvnjkdfbvjdfkbjkdf" +
                                    "vnjdfkvbjkdfbfjrighuiohguidsfhguitrnvbuirthgjithgijtnbjitrhgjidfhahjueriutdf",
                            new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandWithoutSpace_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("done",
                            new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandSpaceBeforeCommand_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand(" done ",
                            new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandSpaceInBetweenCommand_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("d one ",
                            new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandTwoCommandsConcat_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("byelist",
                            new TaskList()).toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }
}
