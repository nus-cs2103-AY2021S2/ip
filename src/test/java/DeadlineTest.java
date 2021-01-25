import duck.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadline = new Deadline("return book","2021-12-22");
    @Test
    public void dummyTest(){
        assertEquals("[D][✘]return book (by: WEDNESDAY,Dec 22 2021)",deadline.getTaskInfo());
        assertEquals("There are 331day(s) before the deadline",deadline.getPeriodDays());
        assertEquals("D | 0 | return book | 2021-12-22",deadline.getTaskInfoOfFile());
        assertEquals("[✘]",deadline.getStatusIcon());
    }
}



