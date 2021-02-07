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
    void listing() {

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

    @Test
    void size() {
        assertEquals(3, tl.size());
    }

    @Test
    void addByTask_todo_success() {
    }

    @Test
    void addByCommand_todo_success() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void remove() {
    }

    @Test
    void print() {
    }
}