package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;

public class StorageTest {
    @Test
    public void loadTest_exceptionThrown() {
        try {
            new Storage(System.getProperty("user.dir") + "/data/xD.txt").load();
            fail();
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! The contents of the loaded file is corrupted, a new file will be created.",
                    ex.toString());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/").load();
            fail();
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! The contents of the loaded file is corrupted, a new file will be created.",
                    ex.toString());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/Duke.txt").load();
        } catch (DukeException ex) {
            fail();
            assertEquals("☹ OOPS!!! The contents of the loaded file is corrupted, a new file will be created.",
                    ex.toString());
        }
    }

    @Test
    public void saveTest_exceptionThrown() {
        try {
            new Storage(System.getProperty("user.dir") + "/data/xD.txt").save(new TaskList());
            fail();
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! Unable to write to Duke.Duke.txt, please try again.", ex.toString());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/").save(new TaskList());
            fail();
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! Unable to write to Duke.Duke.txt, please try again.", ex.toString());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/Duke.txt").save(new TaskList());
        } catch (DukeException ex) {
            fail();
            assertEquals("☹ OOPS!!! Unable to write to Duke.Duke.txt, please try again.", ex.toString());
        }
    }
}
