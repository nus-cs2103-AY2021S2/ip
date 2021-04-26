package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void deadlineConstructorTest() {
        LocalDate date = LocalDate.parse("2020-01-20");
        Deadline deadline = new Deadline("return the book", date);
        assertEquals(deadline.toString(), "[D][ ] return the book(by: 2020-01-20)");
    }
    @Test
    public void deadlineMarkedTest() {
        LocalDate date = LocalDate.parse("2020-01-20");
        Deadline deadline = new Deadline("return the book", date);
        deadline.isDone = true;
        assertEquals(deadline.toString(), "[D][X] return the book(by: 2020-01-20)");
    }
}
