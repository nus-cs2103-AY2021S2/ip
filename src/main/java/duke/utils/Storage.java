package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.dukeexceptions.InvalidFileTaskTypeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Models a utility which reads from local storage and writes to local storage.
 */
public class Storage {
    private String filepath;

    /**
     * Constructs a Storage object responsible for reading and writing to the local storage file.
     *
     * @param filepath the filepath of the local storage file, as a String.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads stored Tasks from local file and returns a TaskList populated with these Tasks.
     * If file is empty or does not exist, an empty TaskList will be returned.
     *
     * @return TaskList populated with Tasks, if applicable.
     */
    public TaskList loadFromFile() throws FileNotFoundException, InvalidFileTaskTypeException {
        File file = new File(filepath);

        List<String> txtInput = new ArrayList<>();
        if (file.exists()) {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                txtInput.add(scannerFile.nextLine());
            }
            scannerFile.close();
        }

        if (txtInput.size() == 0) {
            return new TaskList();
        }

        List<Task> convertedTaskList = TaskStringConverter.listStringToListTask(txtInput);
        assert convertedTaskList.size() > 0;

        return new TaskList(convertedTaskList);
    }

    /**
     * Writes all Tasks to file, and saves file to specified filepath.
     *
     * @param taskList List of all Tasks.
     * @throws IOException if unable to write to file completely.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        if (!taskList.getList().isEmpty()) {
            String stringToWrite = TaskStringConverter.stringTasksForFile(taskList.getList());

            File file = new File(filepath);
            file.getParentFile().mkdirs();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringToWrite);
            fileWriter.close();
        }
    }
}
