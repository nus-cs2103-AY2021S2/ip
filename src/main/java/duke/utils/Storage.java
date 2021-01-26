package duke.utils;

import duke.dukeexceptions.InvalidTaskTypeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filepath;
    private final Ui ui;

    public Storage(String filepath, Ui ui) {
        this.filepath = filepath;
        this.ui = ui;
    }

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

    public void writeToFile(List<String> allStringTasks) throws IOException {
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
