package duke;

import duke.handler.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String STORAGE_PATH;

    public Storage(String storagePath) {
        STORAGE_PATH = storagePath;
    }

    public TaskList load() {
        TaskList tasks = new TaskList();
        try{
            File file = new File(STORAGE_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner dataReader = new Scanner(file);
            while (dataReader.hasNextLine()) {
                tasks.addTask(Parser.parseFromData(dataReader.nextLine()));
            }
            dataReader.close();
            return tasks;
        } catch (IOException e) {
            System.out.println("File did not load");
        }
        return tasks;
    }
}
