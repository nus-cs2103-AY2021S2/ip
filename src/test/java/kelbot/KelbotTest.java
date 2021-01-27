package kelbot;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KelbotTest {
    
    @Test
    public void testCommand() {
        String input = "todo Finish CS2103 Project";
        assertEquals(Command.TODO, new Parser(input).getCommand());
    }
    
    @Test
    public void testTaskList() {
        List<Task> taskList = new ArrayList<>();
        TodoTask todoTask = new TodoTask("Finish CS2103 Project");
        taskList.add(todoTask);
        EventTask eventTask = new EventTask("CS2103 Tutorial", LocalDate.parse("2020-01-27"));
        taskList.add(eventTask);
        assertEquals(eventTask, taskList.remove(1));
    }
    
    @Test
    public void testPrintList() {
        TaskList taskList = new TaskList();
        TodoTask todoTask = new TodoTask("Finish CS2103 Project");
        taskList.add(todoTask);
        EventTask eventTask = new EventTask("CS2103 Tutorial", LocalDate.parse("2020-01-27"));
        taskList.add(eventTask);
        assertEquals(todoTask, taskList.complete(1));
    }
}
