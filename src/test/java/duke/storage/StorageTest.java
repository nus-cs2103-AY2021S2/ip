package duke.storage;

import duke.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void initialiseList_initialListLength_success() throws DukeException {
        String pathName = "./src/test/java/duke/storage/test_data.txt";
        Storage.initialisePath(pathName);
        String dummyTasksString = "DEADLINE /&/ 0 /&/ deadline title /&/ 2001-01-01T23:59\n"
                + "EVENT /&/ 0 /&/ event title /&/ 2001-01-01T23:59\n"
                + "TODO /&/ 0 /&/ todo item\n";

        try {
            new File(pathName).createNewFile();
            FileWriter fw = new FileWriter(pathName);
            fw.write(dummyTasksString);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Storage.initialiseList();
        assertEquals(Storage.getTasks().size(), 3);
    }

    @Test
    void updateDataFile() throws DukeException {
        String filePath = "./src/test/java/duke/storage/test_data.txt";
        Task todo = new Todo("todo");
        Task deadline = new Deadline("deadline", LocalDateTime.now());
        Task event = new Event("event", LocalDateTime.now());

        Storage.initialisePath(filePath);
        Storage.initialiseList();
        Long initialLength = null;

        try {
            initialLength = Files.lines(Path.of(filePath)).count();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Task> tasks = Storage.getTasks();
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        Storage.updateDataFile();

        Long finalLength = null;

        try {
            finalLength = Files.lines(Path.of(filePath)).count();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert initialLength != null;
        assert finalLength != null;
        assertEquals(initialLength + 3, finalLength);
    }
}