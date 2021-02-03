import duke.exceptions.DukeCorruptedStorageException;
import duke.storage.StorageDecoder;
import duke.storage.StorageEncoder;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeStorageTest {

    @Test
    public void testStorage() {
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
        ArrayList<Task> decodedTasks = new ArrayList<>();

        try {
            decodedTasks = StorageDecoder.decodeSave(encodedFile);
        } catch (DukeCorruptedStorageException e) {
            assert false;
        }

        for (int i = 0; i < 6; i++) {
            assertEquals(decodedTasks.get(i).toString(), taskList.get(i).toString());
        }

        String encodedTaskList = "";
        for (String encode : encodedFile) {
            encodedTaskList = encodedTaskList.concat(encode + "\n");
        }

        assertEquals(StorageEncoder.encodeTasks(taskList), encodedTaskList);
    }


}