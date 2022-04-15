import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineConstruction_normalInput_objectCreated() {
        assertEquals("[D][ ] finish project (by: Apr 15 2021 16:00)", new Deadline("finish project",
                LocalDateTime.of(2021, Month.APRIL, 15, 16, 00)).toString());
    }

    @Test
    public void gettingSaveString_inputWithTaskMarkedAsDone_formattedSaveString() {
        Task deadline = new Deadline("finish project",
                LocalDateTime.of(2021, Month.APRIL, 15, 16, 00));
        deadline.markAsDone();
        assertEquals("[D][X] finish project (by: Apr 15 2021 16:00)",
                deadline.toString());
    }

}
