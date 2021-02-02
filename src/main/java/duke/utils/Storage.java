package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Storage {
    private final String filepath;

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
    public TaskList loadFromFile() throws FileNotFoundException, InvalidTaskTypeException {
        File file = new File(filepath);

        List<String> txt = new ArrayList<>();
        if (file.exists()) {
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                txt.add(scannerFile.nextLine());
            }
            scannerFile.close();
        }

        if (txt.size() == 0) {
            return new TaskList();
        } else {
            List<Task> converted = FileTaskStringConverter.allStringToAllTask(txt);
            TaskList taskList = new TaskList(converted);
            taskList.getListInString();
            return taskList;
        }
    }

    /**
     * Writes all Tasks to file, and saves file to specified filepath.
     *
     * @param taskList list of all tasks.
     * @throws IOException if unable to write to file completely.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        List<String> allStringTasks = FileTaskStringConverter.allTaskToAllString(taskList.getList());
        FileWriter fw = new FileWriter(this.filepath);

        StringBuilder text = new StringBuilder();
        for (String s : allStringTasks) {
            text.append(s).append("\n");
        }

        fw.write(text.toString());
        fw.close();
    }
}
