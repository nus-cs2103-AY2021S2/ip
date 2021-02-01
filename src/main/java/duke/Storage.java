package duke;

import duke.handler.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String STORAGE_PATH;
    private static final int MARK_INDEX = 2;

    public Storage(String storagePath) {
        STORAGE_PATH = storagePath;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        Scanner dataReader = null;
        try{
            File file = new File(STORAGE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();

            dataReader = new Scanner(file);
            while (dataReader.hasNextLine()) {
                Task task = Parser.parseFromData(dataReader.nextLine());
                tasks.addTask(task);
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("File did not load");
        } finally {
            dataReader.close();
        }
        return tasks;
    }
}
