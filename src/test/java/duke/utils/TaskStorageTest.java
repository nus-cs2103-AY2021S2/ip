package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskStorageTest {

    @Test
    void writeToFilesTest() {
        String actualOutput = TaskStorage.writeToFiles();
        String expectedOutput = "~~~~~~~~~~~File Saved Successfully!~~~~~~~~~~~~";
        assertEquals(actualOutput, expectedOutput);
    }

    @Test
    void loadFilesTest() {
        String actualOutput = TaskStorage.loadFiles();
        String expectedOutput = "********** Awwww~ You don't have any history of tasks **********\n";
        assertEquals(actualOutput, expectedOutput);
    }

}
