import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ToDoTest {
    @Test
    public void testInitialization() {
        ToDo toDo = new ToDo("description");
        Assertions.assertEquals("[T][ ] description", toDo.toString());

        ToDo toDo2 = new ToDo("description with space");
        Assertions.assertEquals("[T][ ] description with space", toDo2.toString());
    }

    @Test
    public void testDone() {
        ToDo toDo = new ToDo("description");
        toDo.setDone();
        Assertions.assertEquals("[T][X] description", toDo.toString());
    }
}
