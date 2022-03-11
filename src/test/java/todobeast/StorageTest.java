package todobeast;

import org.junit.jupiter.api.Test;
import todobeast.exceptions.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    String testPath = "./testdata/";
    String testFileName = "data.txt";
    String testListData = "testlistdata.txt";

    @Test
    public void testSaveToStorage() throws IOException {

        Storage storage = new Storage(testPath, testFileName);
        storage.saveToStorage("storage test");
        assertEquals("storage test", new Scanner(new File(testPath + testFileName)).nextLine());
    }

    @Test
    public void testLoadTasks() throws FileNotFoundException, InvalidInputException {
        TaskListStub taskListStub = new TaskListStub(new ArrayList<>());
        Storage storage = new Storage(testPath, testListData);
        assertEquals(taskListStub.toString(), storage.loadTasks().toString());
    }
}
