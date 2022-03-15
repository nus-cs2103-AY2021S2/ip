package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    void test() {
        Deadline d1 = new Deadline("deadline", "2021-01-23");
        Deadline d2 = new Deadline("deadline", LocalDate.parse("Jan 23 2021",
                DateTimeFormatter.ofPattern("MMM dd yyyy")));
        System.out.println(d1);
        System.out.println(d2);
        assertEquals(d1.toString(), "[D][ ] deadline(by: Jan 23 2021)");
        assertEquals(d2.toString(), "[D][ ] deadline(by: Jan 23 2021)");
    }
}
