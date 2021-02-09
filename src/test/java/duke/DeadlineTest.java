package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest(){
        try {
            assertEquals(new Deadline("deadline", "2019-10-15").toString(), "[D][ ] deadline (by: Oct 15 2019)");
        } catch (DukeEmptyDescriptionException e) {

        }
    }
}
