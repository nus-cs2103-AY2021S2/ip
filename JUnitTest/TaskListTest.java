import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    List<Task> list = List.of(new ToDo("buy a car"),
                            new Deadline("finish iP", LocalDate.of(2021, Month.AUGUST, 8), LocalTime.of(14, 14)),
                                new Event("casting", LocalDate.of(2021, Month.JANUARY, 27),
                                            LocalTime.of(16, 30), LocalTime.of(17, 0)));
    Task event = list.get(2);
    TaskList tl = new TaskList(list);

    @Test
    void listAllTasks() {
        String correctString = "Here are the tasks in your list:\n"
                + "1.[T][ ] buy a car\n"
                + "2.[D][ ] finish iP (by: Aug 8 2021 02:14PM)\n"
                + "3.[E][ ] casting (at: Jan 27 2021 04:30PM-05:00PM)\n";
        assertEquals(correctString, list.listAllTasks());
    }

    @Test
    void getList() {
        assertEquals(list, tl.getList());
    }

    @Test
    void makeDone() {
        Event e = new Event("casting", LocalDate.of(2021, Month.JANUARY, 27),
                LocalTime.of(16, 30), LocalTime.of(17, 0));
        e.isDone = true;
        assertEquals(e.toString(), tl.makeDone(2));
    }
}