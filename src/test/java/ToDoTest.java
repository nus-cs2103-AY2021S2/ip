import org.junit.Assert;
import org.junit.Test;

public class ToDoTest {
    @Test
    public void testInitialization() {
        ToDo toDo = new ToDo("description");
        Assert.assertEquals("[T][ ] description", toDo.toString());

        ToDo toDo2 = new ToDo("description with space");
        Assert.assertEquals("[T][ ] description with space", toDo2.toString());
    }

    @Test
    public void testDone() {
        ToDo toDo = new ToDo("description");
        toDo.setDone();
        Assert.assertEquals("[T][X] description", toDo.toString());
    }
}
