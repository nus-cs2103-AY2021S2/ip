package duke.utils;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskStringConverterTest {
    @Test
    public void stringToTaskTest() {
        String inputFromFile = "E | 0 | project meeting | 15/12/2020 0911";
        List<String> allInput = new ArrayList<>();
        allInput.add(inputFromFile);
        try {
            List<Task> allTasks = TaskStringConverter.listStringToListTask(allInput);
            assertEquals("[E][ ] project meeting (at: 15 Dec 2020, 9:11 AM)",
                    allTasks.get(0).toString());
        } catch (InvalidTaskTypeException e){
            System.err.println(e.getMessage());
        }
    }
}
