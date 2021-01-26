import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void displayTest() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("sample todo");

        LocalDate date2 = LocalDate.parse("27/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Task task2 = new Event("sample event", date2);

        LocalDate date3 = LocalDate.parse("25/12/2050", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Task task3 = new Deadline("sample deadline", date3);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        String expectedString = "1. [T][ ] sample todo\n" +
                "2. [E][ ] sample event (on: 27 Oct 2021)\n" +
                "3. [D][ ] sample deadline (by: 25 Dec 2050)";

        assertEquals(expectedString, tasks.display());
    }
}