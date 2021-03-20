import duke.Deadline;
import duke.Event;
import duke.TaskList;
import duke.Todo;
import duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    void showAllTasksTest() {
        Ui ui = new Ui();

        TaskList testTaskList = new TaskList();
        Todo todoStub = new Todo("sample todo");
        Event eventStub = new Event("sample event",
                LocalDate.parse("28/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Deadline deadlineStub = new Deadline("sample deadline",
                LocalDate.parse("10/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testTaskList.add(todoStub);
        testTaskList.add(eventStub);
        testTaskList.add(deadlineStub);

        String expectedResult = "Here's the current state of your to-do list:\n"
                + "1. [T][ ] sample todo\n"
                + "2. [E][ ] sample event (on: 28 Sep 2021)\n"
                + "3. [D][ ] sample deadline (by: 10 Oct 2021)";

        String actualResult = ui.showAllTasks(testTaskList);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void showFoundTasksTest() {
        Ui ui = new Ui();

        TaskList testFoundTaskList = new TaskList();
        Todo foundTodoStub = new Todo("sample todo");
        Event foundEventStub = new Event("sample event",
                LocalDate.parse("28/09/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Deadline foundDeadlineStub = new Deadline("sample deadline",
                LocalDate.parse("10/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        testFoundTaskList.add(foundTodoStub);
        testFoundTaskList.add(foundEventStub);
        testFoundTaskList.add(foundDeadlineStub);

        String expectedResult = "Here's all the matches I found:\n"
                + "- [T][ ] sample todo\n"
                + "- [E][ ] sample event (on: 28 Sep 2021)\n"
                + "- [D][ ] sample deadline (by: 10 Oct 2021)";

        String actualResult = ui.showFoundTasks(testFoundTaskList);

        assertEquals(expectedResult, actualResult);
    }
}