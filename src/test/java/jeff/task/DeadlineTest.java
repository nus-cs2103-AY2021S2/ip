package jeff.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void getSymbolTest() {
        Deadline deadline = new Deadline("return book", "2020-01-01", "09:59");
        assertEquals("D", deadline.getSymbol());
    }

    @Test
    public void getDateTest() {
        Deadline deadline = new Deadline("return book", "2020-01-01", "09:59");
        assertEquals(LocalDate.parse("2020-01-01"), deadline.getDate());
    }

    @Test
    public void getTimeTest() {
        Deadline deadline = new Deadline("return book", "2020-01-01", "09:59");
        assertEquals(LocalTime.parse("09:59"), deadline.getTime());
    }

    @Test
    public void toStringTest1() {
        Deadline deadline = new Deadline("return book", "2020-01-01", "09:59");
        assertEquals("[D][  ] return book (by: 1 JANUARY 2020 09:59)", deadline.toString());
    }

    @Test
    public void toStringTest2() {
        Deadline deadline = new Deadline("return book", "2020-01-01", "09:59");
        deadline.setDone();
        assertEquals("[D][X] return book (by: 1 JANUARY 2020 09:59)", deadline.toString());
    }
}

