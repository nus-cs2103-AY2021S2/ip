import duck.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("return book","2021-12-22");
    @Test
    public void dummyTest(){
        assertEquals("[E][✘]return book (at: WEDNESDAY,Dec 22 2021)",event.getTaskInfo());
        assertEquals("There are 331day(s) before the event starting",event.getPeriodDays());
        assertEquals("E | 0 | return book | 2021-12-22",event.getTaskInfoOfFile());
        assertEquals("[✘]",event.getStatusIcon());
    }
}