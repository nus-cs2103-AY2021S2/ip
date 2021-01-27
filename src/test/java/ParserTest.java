import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void convertTasksToStringTest(){
        List<Task> lst = new ArrayList<>();
        TaskList taskList = new TaskList(lst);
        ToDo task1 = new ToDo("todo 1");
        Deadline task2 = new Deadline("deadline 1", LocalDate.parse("2020-08-31"), LocalTime.parse("18:17"));
        Event task3 = new Event("event 1", LocalDate.parse("2021-09-17"), LocalTime.parse("08:09"));
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        String result = Parser.convertTasksToString(taskList);
        assertEquals("", result);
    }
}
