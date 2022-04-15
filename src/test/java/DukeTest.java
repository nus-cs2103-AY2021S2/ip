import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void AddCommandTest() {
        Command c = new AddCommand("todo","Homework","");
        assertEquals(false, c.isExit());
    }

    @Test
    public void ExitCommandTest() {
        Command c = new ExitCommand("","","");
        assertEquals(true, c.isExit());
    }

    @Test
    public void EventTest(){
        Task task = new Events("Lab Due Date", "2021-02-02");
        assertEquals(LocalDate.parse("2021-02-02"), task.getTime());
    }
    @Test
    public void DeadlineTest(){
        Task task = new Deadline("Lab Due Date", "2021-02-02");
        assertEquals(LocalDate.parse("2021-02-02"), task.getTime());
        assertEquals("[D][ ] Lab Due Date(by: Feb 2 2021)", task.toString());
    }
    @Test
    public void TodoTest(){
        Task task = new ToDos("Assignment");
        assertEquals("[T][ ] Assignment", task.toString());
    }

}
