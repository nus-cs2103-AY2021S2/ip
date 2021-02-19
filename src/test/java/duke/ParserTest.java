package duke;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    public void exitTest() {
        try {
            Assertions.assertNull(Parser.parse("bye"));
            Assertions.assertNull(Parser.parse("exit"));
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
    @Test
    public void listTest() {
        try {
            Assertions.assertTrue(Parser.parse("list") instanceof ListCommand);
            Assertions.assertTrue(Parser.parse(" ls ") instanceof ListCommand);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
    @Test
    public void todoTest() {
        try {
            Command c = Parser.parse("TODO blow up the moon");
            Assertions.assertTrue(c instanceof AddCommand);
            Assertions.assertEquals(2, c.getCommandParameters().length);
            Assertions.assertEquals("T", c.getCommandParameters()[0]);
            Assertions.assertEquals("blow up the moon", c.getCommandParameters()[1]);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
}
