package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test_toString() {
        assertEquals("E | 0 | meeting | Dec 21 2020 Sunday", new Event("meeting", "2020-12-21 Sunday").toString());
    }
}
