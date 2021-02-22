package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import command.Command;
import command.CommandDetails;
import command.DukeCommand;

import utility.Parser;

public class ParserTest {

    @Test
    public void parseCommandForInvalid() {
        try {
            Command c = Parser.parseCommand("   abcdefg i love damith").getCommand();
            assertEquals(Command.INVALID, c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseCommandForFind() {
        try {
            DukeCommand dukeCommand = Parser.parseCommand("find lovexoxo");
            assertEquals(Command.FIND, dukeCommand.getCommand());
            assertEquals(new CommandDetails(Command.FIND, "lovexoxo"), dukeCommand.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseCommandForDelete() {
        try {
            DukeCommand dukeCommand = Parser.parseCommand("delete 10");
            assertEquals(Command.DELETE, dukeCommand.getCommand());
            assertEquals(new CommandDetails(Command.DELETE, "10"), dukeCommand.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseCommandForTag() {
        try {
            DukeCommand dukeCommand = Parser.parseCommand("tag add \"CS2103T_tP\" intenseAF");
            assertEquals(Command.TAG, dukeCommand.getCommand());
            assertEquals(new CommandDetails(Command.TAG, "add", "CS2103T_tP", "intenseAF"), dukeCommand.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void parseCommandForBye() {
        try {
            Command c = Parser.parseCommand("bye").getCommand();
            assertEquals(Command.BYE, c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Test
    public void parseCommandForDone() {

        try {
            DukeCommand dukeCommand = Parser.parseCommand("done 1");
            assertEquals(Command.DONE, dukeCommand.getCommand());
            assertEquals(new CommandDetails(Command.DONE, "1"), dukeCommand.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Test
    public void parseCommandForToDo() {

        try {
            DukeCommand c = Parser.parseCommand("todo todo1");
            assertEquals(Command.TODO, c.getCommand());
            assertEquals(new CommandDetails(Command.TODO, "todo1"), c.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Test
    public void parseCommandForDeadline() {
        try {
            DukeCommand c = Parser.parseCommand("deadline deadline1 /by 2020-04-13");
            assertEquals(Command.DEADLINE, c.getCommand());
            assertEquals(new CommandDetails(Command.DEADLINE, "deadline1", "2020-04-13"), c.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Test
    public void parseCommandForEvent() {
        try {
            DukeCommand c = Parser.parseCommand("event event1 /at 2020-04-13 2-4pm");
            assertEquals(Command.EVENT, c.getCommand());
            assertEquals(new CommandDetails(Command.EVENT, "event1", "2020-04-13", "2-4pm"), c.getDetails());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @Test
    public void parseCommandForList() {
        try {
            Command c = Parser.parseCommand("list").getCommand();
            assertEquals(Command.LIST, c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
