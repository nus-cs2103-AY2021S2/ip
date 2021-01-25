package duke.interaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.common.DukeException;
import duke.task.TaskList;

public class Storage {
    private final File saveFile;

    public Storage(String filePath) {
        this.saveFile = new File(filePath);
    }

    public TaskList readTasks() {
        try (Scanner sc = new Scanner(saveFile)) {
            return TaskList.deserialise(sc);
        } catch (DukeException.StorageReadError e) {
            System.err.println(e);



            return new TaskList();
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    public void writeTasks(TaskList taskList) {
        this.saveFile.getParentFile().mkdirs();

        try {
            this.saveFile.createNewFile();

            FileWriter fw = new FileWriter(saveFile);
            fw.write(taskList.serialise());
            fw.close();
        } catch (IOException e) {
            throw new DukeException.StorageWriteError();
        }

    }
}
