import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duck.task.Deadline;

public class DeadlineTest {
    private Deadline deadline = new Deadline("return book", "2021-12-22");

    @Test
    public void dummyTest() {
        assertEquals("D | 0 | return book | 2021-12-22", deadline.getTaskInfoOfFile());
        assertEquals("[\u2718]", deadline.getStatusIcon());
    }
}



