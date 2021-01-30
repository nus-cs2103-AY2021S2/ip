package duke.storage;

import duke.exceptions.EmptyTaskDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DukeFileReader {
    private static final String FILE_PATH = "data" + File.separator + "duke.txt";

    private static TaskList readTaskListFromInternalStorage() throws FileNotFoundException {
        TaskList taskList = new TaskList();
        FileDirectoryChecker.prepareFile(FILE_PATH);
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            String[] taskBreakdown = taskString.split("\\|");
//            for (String str : taskBreakdown) {
//                System.out.println(str + "\n");
//            }
            boolean isTaskDone = taskBreakdown[1].equals("1") ? true : false;
            String taskName = taskBreakdown[2];
            if (taskBreakdown[0].equals("T")) {
                try {
                    Task task = new Todo(taskName);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("D")) {
                try {
                    Task task = new Deadline(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("E")) {
                try {
                    Task task = new Event(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.addTask(task);
                } catch (EmptyTaskDukeException e) {
                    Ui.printError(e.getMessage());
                }
            }
        }
        return taskList;
    }

    public static TaskList loadTasks() {
        try {
            TaskList taskList = readTaskListFromInternalStorage();
            return taskList;
        } catch (FileNotFoundException e) {
            Ui.printError(e.getMessage());
        }
        return new TaskList();
    }
}
