package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.ErrorBox;
import duke.ui.Ui;

/**
 * This class handles the saving and loading of the taskList to/from a specific FilePath.
 */
public class TaskStorage {

    private static final String FILEPATH = "data/Duke.txt";
    private static final String DIRECTORY = "data";

    /**
     * This method writes the content of a taskList to data/Duke.txt.
     *
     * @return A string display of a successful save.
     */
    public static String writeToFiles() {
        List<Task> taskList = TaskList.getTaskList();
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task t : taskList) {
                String temp = t.getType() + "@@" + t.getDoneStatus()
                    + "@@" + t.getTaskName() + (t.getDate().equals("")
                    ? "" : ("@@" + t.getDate())) + System.lineSeparator();
                fw.write(temp);
            }
            fw.close();
            taskList.clear();
            assert taskList.size() == 0 : "TaskList not clear";
        } catch (IOException err) {
            ErrorBox.display(err.getMessage());
        }
        return Ui.SUCCESSFUL_SAVE;
    }


    /**
     * This method loads the file from data/Duke.txt and display in a list format when the program starts.
     *
     * @return the String display of a successful load.
     */
    public static String loadFiles() {
        StringBuilder loadContent = new StringBuilder();

        try {
            File directory = new File(DIRECTORY);
            File file = new File(FILEPATH);

            if (!directory.exists()) {
                directory.mkdir();
            }

            assert directory.exists() : "Directory does not exist";

            if (!file.exists()) {
                file.createNewFile();
            }

            assert file.exists() : "File does not exist";

            if (file.length() == 0) {
                return Ui.EMPTY_FILE;
            }

            loadContent.append(restoreTask(file));
        } catch (IOException e) {
            ErrorBox.display("IO error!: " + e.getMessage());
        }
        if (loadContent.length() == 0) {
            loadContent.append(Ui.EMPTY_FILE);
        } else {
            loadContent.insert(0, Ui.SUCESSFUL_LOAD);
        }

        return loadContent.toString();
    }

    /**
     * This method restore the task List from Duke.txt.
     * @param file Duke.txt that store the user past history of task list.
     * @return the file content in String.
     * @throws IOException thrown when file is not found.
     */
    private static String restoreTask(File file) throws IOException {
        Scanner sc = new Scanner(file);
        StringBuilder fileContent = new StringBuilder("");

        //Interpret the file content and add tasks to task list.
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split("@@");
            if (line.length == 3) {
                Todo t = new Todo(line[2], line[1]);
                TaskList.addTask(t);
                fileContent.append(t.toString() + "\n");
            } else {
                int type = Integer.parseInt(line[0]);
                String done = line[1];
                String taskName = line[2];
                String date = line[3];
                Task t = type == 2 ? new Deadline(taskName, date, done) : new Event(taskName, date, done);
                TaskList.addTask(t);
                fileContent.append(t.toString() + "\n");
            }
        }
        sc.close();
        return fileContent.toString();
    }
}
