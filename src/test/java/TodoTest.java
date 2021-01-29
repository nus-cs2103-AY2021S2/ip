import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringCoversion() {
        assertEquals("[T][INPROGRESS] read book",
                new Todo("read book").toString());
    }
}
