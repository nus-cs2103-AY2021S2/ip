package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void createTodos(){
        Task test = new ToDos(false, "test");
        assertEquals("[T] [ ] test", test.toString());
    }
}
