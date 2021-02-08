package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void fileString_simpleInput_stringReturned() {
        assertEquals("T | false | buy books", new Todo("buy books").toFileString());
    }
}
