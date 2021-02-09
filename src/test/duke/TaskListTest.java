import duke.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {

    @Test
    public void testToString() {
        String EventDetails = " [T][✘] hello\n" +
                                " [E][✘] event (at: lanflaf)\n" +
                                " [D][✘] deadline (by: 1999-12-12)";

        Todo newTodo = new Todo("hello");
        Event newEvent = new Event("event", "lanflaf");
        LocalDate ld = LocalDate.parse("1999-12-12");
        Deadline newDeadline = new Deadline("deadline", ld);
        ArrayList<Task> tl = new ArrayList<>();
        tl.add(newTodo);
        tl.add(newEvent);
        tl.add(newDeadline);
        TaskList newTaskList = new TaskList(tl);

        String actual = newTaskList.toString();
        String expected = EventDetails;
        assertEquals(expected, actual);
    }
}
