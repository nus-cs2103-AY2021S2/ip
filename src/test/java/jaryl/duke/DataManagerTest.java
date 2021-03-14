package jaryl.duke;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest {
    private static final String FILE_PATH = "./data/test.txt";
    private ArrayList<Task> tasksList = new ArrayList<>();

    @Test
    public void testWriteToFile() throws InvalidFormatException {
        DataManager dataManager = new DataManager(FILE_PATH);

        if(Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.delete(Paths.get(FILE_PATH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tasksList.add(new Todo("Math Homework"));
        tasksList.add(new Deadline("Science Homework", "02/12/2021 1800"));
        tasksList.add(new Event("Jay Chou Concert", "02/12/2021 1800"));

        dataManager.writeToFile(tasksList);

        assert(Files.exists(Paths.get(FILE_PATH)));
    }

    @Test
    public void testReadFromFile() throws DukeException {
        DataManager dataManager = new DataManager(FILE_PATH);

        if(Files.exists(Paths.get(FILE_PATH))) {
            try {
                Files.delete(Paths.get(FILE_PATH));
                String data = "T | 0 | Math Homework" +
                        "\nD | 0 | Science Homework | 02 Dec 2021 1800" +
                        "\nE | 0 | Jay Chou Concert | 02 Dec 2021 1800";
                Files.write(Paths.get(FILE_PATH), data.getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                tasksList = dataManager.readFromFile();
            } catch (DukeException e) {
                e.printStackTrace();
            }

            assertEquals(tasksList.size(), 3);
        }
    }

}
