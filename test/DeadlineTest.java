import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void constructor_normalInputs_taskUncompleted() {
        LocalDate date = LocalDate.of(2021, 01, 01);
        LocalTime time = LocalTime.of(12, 34);

        assertEquals(false, new Deadline("test", date, time).isDone());
    }

    @Test
    public void outputFormat_normalInputs_correctFormat() {
        LocalDate date = LocalDate.of(2021, 01, 01);
        LocalTime time = LocalTime.of(12, 34);

        assertEquals("D 0 test (by: 1 Jan 2021 1234 )",
                new Deadline("test", date, time).taskStatus());
    }
}
