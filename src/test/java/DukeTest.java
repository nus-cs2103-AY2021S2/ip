import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void deadlineTaskString(){
        assertEquals("[D][ ] tasktest (by: 01 February 2020)",
                new Deadlines("tasktest", "2020-02-01").toString());
    }

    @Test
    public void eventsTaskString(){
        assertEquals("[E][ ] tasktest (at: 01 February 2020 08:00-09:00)",
                new Events("tasktest", "2020-02-01 0800-0900").toString());
    }

}
