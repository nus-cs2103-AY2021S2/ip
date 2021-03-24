import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void uncompletedDeadlineOutputTest() {
        String input = "deadline First Deadline /by 2020-01-01";
        String[] inputArr = input.split(" ", 2);
        String[] temp = inputArr[1].split("/", 2);
        assertEquals(
                "[D][ ] First Deadline (JAN 01 2020)",
                new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], false).toString()
        );
    }

    @Test
    public void completedDeadlineOutputTest() {
        String input = "deadline First Deadline /by 2020-01-01";
        String[] inputArr = input.split(" ", 2);
        String[] temp = inputArr[1].split("/", 2);
        assertEquals(
                "[D][X] First Deadline (JAN 01 2020)",
                new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], true).toString()
        );
    }

    @Test
    public void deadlineSetAsDoneOutputTest() {
        String input = "deadline First Deadline /by 2020-01-01";
        String[] inputArr = input.split(" ", 2);
        String[] temp = inputArr[1].split("/", 2);
        assertEquals(
                "[D][X] First Deadline (JAN 01 2020)",
                new Deadline(LocalDate.parse(temp[1].substring(3)), temp[0], false).setAsDone().toString()
        );
    }

}
