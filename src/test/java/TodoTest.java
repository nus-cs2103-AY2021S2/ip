import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] example todo task", new ToDo("example todo task").toString());
    }

    @Test
    public void getDataTest() {
        assertEquals("T!@#0!@#example todo task",new ToDo("example todo task").getData());
    }
}
