package java.duke;

import duke.Deadline;
import duke.DukeEmptyDescriptionException;
import duke.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void eventTest(){
        try {
            assertEquals(new Deadline("deadline", "2019-10-15").toString(), "[D]deadline (by: Oct 15 2019)");
        } catch (DukeEmptyDescriptionException e) {

        }
    }
}