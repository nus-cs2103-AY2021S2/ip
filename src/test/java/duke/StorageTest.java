package duke;

import duke.system.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void commandExtractionTest(){
        assertEquals("deadline", new Parser("deadline return book /by Sunday").getCommand());
    }
}
