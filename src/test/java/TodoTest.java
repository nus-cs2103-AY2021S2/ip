import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    @Test
    public void test1() {
        assertEquals("[T]✘ read book",
                new Todo("read book").toString());
    }

    @Test
    public void test2() {
        Todo test = new Todo("read book");
        test.markAsDone();
        assertEquals("\u2713", test.getStatusIcon());
    }

}
