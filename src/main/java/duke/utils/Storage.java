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
    private final Ui ui;

    /**
     * Constructs a Storage object responsible for reading and writing to the local storage file.
     * @param filepath the filepath of the local storage file, as a String.
     * @param ui the object in charge of printing user-friendly outputs.
     */
    public Storage(String filepath, Ui ui) {
        this.filepath = filepath;
        this.ui = ui;
    }

    /**
     * Loads stored Tasks from local file and returns a TaskList populated with these Tasks.
     * If file is empty or does not exist, an empty TaskList will be returned.
     *
     * @return TaskList populated with Tasks, if applicable.
     */
    public TaskList loadFromFile() {
        try {
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
                this.emptyFile();
                return new TaskList();
            } else {
                this.nonEmptyFile();
                List<Task> converted = FileTaskStringConverter.allStringToAllTask(txt);
                TaskList taskList = new TaskList(converted);
                taskList.printList();
                return taskList;
            }
        } catch (FileNotFoundException e) {
            ui.showError("Cannot access file at specified location.\n" + e.getMessage());
            return new TaskList();
        } catch (InvalidTaskTypeException e) {
            ui.showError("Erroneous task type in file. Please check your file again!");
            return new TaskList();
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

    private void emptyFile() {
        this.ui.showMsg("You have no existing tasks!");
    }

    private void nonEmptyFile() {
        this.ui.showMsg("You have existing tasks!");
    }
}
