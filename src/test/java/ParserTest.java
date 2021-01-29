import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;


public class ParserTest {

    @Test
    public void parseCommand_bye() throws DukeException {
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("bye").toString());
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("BYE").toString());
        assertEquals("Test usage: this is an EXIT command",
                Parser.parseCommand("bYe").toString());
    }

    @Test
    public void parseCommand_list() throws DukeException {
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("list").toString());
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("LIST").toString());
        assertEquals("Test usage: this is a LIST command",
                Parser.parseCommand("LisT").toString());
    }

    @Test
    public void parseCommand_deadline() throws DukeException {
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("deadline ").toString());
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("DEADLINE ").toString());
        assertEquals("Test usage: this is a DEADLINE command",
                Parser.parseCommand("DeadLINe ").toString());
    }

    @Test
    public void parseCommand_event() throws DukeException {
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("event ").toString());
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("EVENT ").toString());
        assertEquals("Test usage: this is an EVENT command",
                Parser.parseCommand("EvenT ").toString());
    }

    @Test
    public void parseCommand_todo() throws DukeException {
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("todo ").toString());
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("TODO ").toString());
        assertEquals("Test usage: this is a TODO command",
                Parser.parseCommand("TodO ").toString());
    }

    @Test
    public void parseCommand_delete() throws DukeException {
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("delete ").toString());
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("DELETE ").toString());
        assertEquals("Test usage: this is a DELETE command",
                Parser.parseCommand("dElEte ").toString());
    }

    @Test
    public void parseCommand_done() throws DukeException {
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("done ").toString());
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("DONE ").toString());
        assertEquals("Test usage: this is a DONE command",
                Parser.parseCommand("DoNe ").toString());
    }

    @Test
    public void parseCommand_invalidCommandOneLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("a").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandZeroLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandLongLetter_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("frhyuighrdiughiudfhgvijverhfiueeidfhaijfjiorejgiorshjglrkehgjklergaa"
                            + "ghdfjkhvehrghiuyerhgidfbvijergfvnjrkdbgnvjkdfbnvjkfdnvjkdfbvnjkdfbvjdfkbjkdf"
                            + "vnjdfkvbjkdfbfjrighuiohguidsfhguitrnvbuirthgjithgijtnbjitrhgjidfhahjueriutdf")
                            .toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandWithoutSpace_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("done").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandSpaceBeforeCommand_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand(" done ").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandSpaceInBetweenCommand_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("d one ").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommandTwoCommandsConcat_exceptionThrown() {
        try {
            assertEquals("",
                    Parser.parseCommand("byelist").toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid command", e.getMessage());
        }
    }
}
