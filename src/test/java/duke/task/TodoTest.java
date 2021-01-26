package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void fileString_simpleInput_stringReturned() {
        assertEquals("T | false | buy books", new Todo("buy books").fileString());
    }
}