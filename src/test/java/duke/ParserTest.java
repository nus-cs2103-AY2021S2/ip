package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void exitTest(){
        try {
            Assertions.assertNull(Parser.parse("bye"));
            Assertions.assertNull(Parser.parse("exit"));
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
    @Test
    public void listTest(){
        try {
            Assertions.assertTrue(Parser.parse("list") instanceof ListCommand);
            Assertions.assertTrue(Parser.parse(" ls ") instanceof ListCommand);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
    @Test
    public void todoTest(){
        try {
            Command c = Parser.parse("TODO blow up the moon");
            Assertions.assertTrue(c instanceof AddCommand);
            Assertions.assertEquals(2, c.run().length);
            Assertions.assertEquals("T", c.run()[0]);
            Assertions.assertEquals("blow up the moon", c.run()[1]);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }
}
