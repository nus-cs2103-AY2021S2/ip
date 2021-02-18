import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void DeadlineTestOne() {
        assertEquals("[D][ ] finish project (by: Apr 15 2021 16:00)", new Deadline("finish project",
                LocalDateTime.of(2021, Month.APRIL, 15, 16, 00)).toString());
    }

    @Test
    public void TodoTestFour() {
        Task deadline = new Deadline("finish project",
                LocalDateTime.of(2021, Month.APRIL, 15, 16, 00));
        deadline.markAsDone();
        assertEquals("[D][X] finish project (by: Apr 15 2021 16:00)",
                deadline.toString());
    }

}
