import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList taskList = new TaskList();
    ToDo todo = new ToDo("run");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse("2/12/2019",formatter);
    Deadline deadline = new Deadline("return book", date);
    Event event = new Event("event project meeting", "Mon 2-4pm") ;

    @Test
    public void testNumOfTask() {
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        assertEquals(3, taskList.numOfTasks());
    }
}