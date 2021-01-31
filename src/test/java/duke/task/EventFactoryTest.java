package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EventFactoryTest {
    @Test
    void read_emptyInput_exceptionThrown(){
        try {
            assertEquals("",new EventFactory().createTask("").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Description of event cannot be empty",e.getMessage());
        }
    }
    @Test
    void read_wrongInput_exceptionThrown(){
        try {
            assertEquals("", new EventFactory().createTask("test").toString());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Error: Please add a start time to your event",e.getMessage());
        }
    }
}
