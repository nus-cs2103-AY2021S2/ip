package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test_toString() {
        assertEquals("D | 0 | running | Dec 21 2020 Sunday", new Deadline("running", "2020-12-21 Sunday").toString());
    }
}
