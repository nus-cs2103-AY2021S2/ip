import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void TodoTestOne() {
        assertEquals("T | 0 | finish project",new Todo("finish project").getSaveString());
    }

    @Test
    public void TodoTestFour() {
        Task todo = new Todo("finish project");
        todo.markAsDone();
        assertEquals("T | 1 | finish project",todo.getSaveString());
    }

    @Test
    public void TodoTestTwo() {
        assertEquals("[T][ ] finish project",new Todo("finish project").toString());
    }

    @Test
    public void TodoTestThree() {
        Task todo = new Todo("finish project");
        todo.markAsDone();
        assertEquals("[T][X] finish project", todo.toString());
    }

}