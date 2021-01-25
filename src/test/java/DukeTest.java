import duke.storage.StorageEncoder;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testStorage(){
        ArrayList<String> encodedFile = new ArrayList<>();
        encodedFile.add("T | 0 | todo task not done");
        encodedFile.add("T | 1 | todo task done");
        encodedFile.add("D | 0 | deadline task not done | 2020-01-25");
        encodedFile.add("D | 1 | deadline task done | 2020-01-20");
        encodedFile.add("E | 0 | event task not done | 2020-02-20");
        encodedFile.add("E | 1 | event task done | 2020-02-19");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("todo task not done"));
        Todo todo2 = new Todo("todo task done");
        todo2.done();
        taskList.add(todo2);
        taskList.add(new Deadline("deadline task not done", LocalDate.parse("2020-01-25")));
        Deadline deadline2 = new Deadline("deadline task done", LocalDate.parse("2020-01-20"));
        deadline2.done();
        taskList.add(deadline2);
        taskList.add(new Event("event task not done", LocalDate.parse("2020-02-20")));
        Event event2 = new Event("event task done", LocalDate.parse("2020-02-19"));
        event2.done();
        taskList.add(event2);
        /*
        try {
            assertEquals(StorageDecoder.decodeSave(encodedFile), taskList);
        } catch (Exception e) {
            assert false;
        }

         */
        String encodedTaskList = "";
        for (String encode : encodedFile) {
            encodedTaskList = encodedTaskList.concat(encode + "\n");
        }

        assertEquals(StorageEncoder.encodeTasks(taskList), encodedTaskList);
    }

    @Test
    public void testTask() {
        Task[] tasks = new Task[] {new Todo("a"), new Deadline("b",
                LocalDate.parse("2020-01-01")), new Event("c", LocalDate.parse("2020-01-01"))};
        String[] strTasksNotDone = new String[] {"[T][ ] a", "[D][ ] b (by: Jan 01 2020)",
                "[E][ ] c (at: Jan 01 2020)"};
        String[] strTasksDone = new String[] {"[T][X] a", "[D][X] b (by: Jan 01 2020)",
                "[E][X] c (at: Jan 01 2020)"};
        for (int i = 0; i < 3; i++) {
            assertEquals(tasks[i].toString(), strTasksNotDone[i]);
        }
        for (Task task : tasks) {
            task.done();
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(tasks[i].toString(), strTasksDone[i]);
        }
    }
}