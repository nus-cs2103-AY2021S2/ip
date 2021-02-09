package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.dukeexceptions.InvalidFileTaskTypeException;
import duke.tasks.Task;

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
        } catch (InvalidFileTaskTypeException e) {
            System.err.println("Test failed! " + e.getMessage());
        }
    }
}
