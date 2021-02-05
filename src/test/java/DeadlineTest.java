import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        Deadline  deadline = new Deadline("return book", "2/12/2019 1800", false);
        assertEquals("[D][ ] return book (by: Dec 2 2019 6:00 PM)", deadline.toString());
    }

    @Test
    public void toSaveFormatTest(){
        Deadline  deadline = new Deadline("return book", "2/12/2019 1800", false);
        assertEquals("D | 0 | return book | Dec 2 2019 6:00 PM", deadline.toSaveFormat());
    }
}