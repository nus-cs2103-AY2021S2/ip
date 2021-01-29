package duke.tasklist;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    private static TaskList tl = new TaskList();

    @Test
    public void addingTaskTest(){
        tl.addToDo("testing description");
        tl.addDeadline("test deadline", LocalDate.of(2021, 1,25),
                LocalTime.NOON);
        tl.addEvent("test event", LocalDate.of(2021, 1,26),
                LocalTime.NOON, LocalTime.MIDNIGHT);

        assertEquals("[T][ ] testing description",  tl.getTaskListArray().get(1).toString());
        assertEquals("[D][ ] test deadline (by: 2021-01-25 12:00)",  tl.getTaskListArray().get(2).toString());
        assertEquals("[E][ ] test event(at: 2021-01-26 12:00 00:00)",  tl.getTaskListArray().get(3).toString());
    }

    @Test
    public void markAsDoneTest(){
        tl.addToDo("do ip");
        tl.markAsDone(0);
        assertEquals("[T][✘] do ip", tl.getTaskListArray().get(0).toString());
    }

    @Test
    public void deleteTaskTest(){
        tl.deleteTask(0);
        assertEquals("[T][ ] testing description", tl.getTaskListArray().get(0).toString());
    }

    }
