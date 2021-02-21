package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.TaskList;

public class StorageTest {
    @Test
    public void loadStorage() {
        try {
            new Storage(System.getProperty("user.dir")).loadData();
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! The file cannot be loaded and a new file will be created !!!",
                    ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.home")).loadData();
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! The file cannot be loaded and a new file will be created !!!",
                    ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/DukeBot.txt").loadData();
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! The file cannot be loaded and a new file will be created !!!",
                    ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/Duke.txt").loadData();
            System.out.println("Successfully loaded file!");
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! Unfortunately, file configuration is not working at the moment "
                    + ":-( Pls restart (exit and start again) the Duke Bot !!!", ex.getMessage());
        }
    }

    @Test
    public void saveStorage() {
        try {
            new Storage(System.getProperty("user.dir")).saveData(new TaskList());
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! Unfortunately, file saving to Duke.txt is not working at the moment "
                    + ":-( Pls kindly try again later !!!", ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.home")).saveData(new TaskList());
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! Unfortunately, file saving to Duke.txt is not working at the moment "
                    + ":-( Pls kindly try again later !!!", ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/DukeBot.txt").saveData(new TaskList());
        } catch (DukeException ex) {
            System.out.println("Fail to load file!");
            assertEquals("☹ OOPS!!! Unfortunately, file saving to Duke.txt is not working at the moment "
                    + ":-( Pls kindly try again later !!!", ex.getMessage());
        }

        try {
            new Storage(System.getProperty("user.dir") + "/data/Duke.txt").saveData(new TaskList());
            System.out.println("Successfully loaded file!");
        } catch (DukeException ex) {
            assertEquals("☹ OOPS!!! Unfortunately, file saving to Duke.txt is not working at the moment "
                    + ":-( Pls kindly try again later !!!", ex.getMessage());
        }
    }
}
