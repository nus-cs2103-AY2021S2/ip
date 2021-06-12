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

/**
 * Reads file input from internal storage
 */
public class DukeFileReader {

    private static final String TODO_IDENTIFIER = "T";
    private static final String DEADLINE_IDENTIFIER = "D";
    private static final String EVENT_IDENTIFIER = "E";

    private static final String TASK_COMPLETED_NUM = "1";

    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_COMPLETION_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_DATE_INDEX = 3;

    /**
     * loads tasks from internal storage
     * @return a task list containing all the tasks from internal storage
     */
    public static TaskList loadTasks(String filePath) {
        try {
            TaskList taskList = readTaskListFromInternalStorage(filePath);
            return taskList;
        } catch (FileNotFoundException e) {
            Ui.printError(e.getMessage());
        }
        return new TaskList();
    }

    private static TaskList readTaskListFromInternalStorage(String filePath) throws FileNotFoundException {
        TaskList taskList = new TaskList();
        FileDirectoryChecker.prepareFile(filePath);
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            String[] taskBreakdown = getTaskBreakdownInArray(taskString);
            boolean isTaskDone = taskBreakdown[TASK_COMPLETION_INDEX].equals(TASK_COMPLETED_NUM);
            String taskDescription = taskBreakdown[TASK_DESCRIPTION_INDEX];
            addTasksToTaskList(taskList, taskBreakdown, isTaskDone, taskDescription);
        }
        sc.close();
        return taskList;
    }

    private static void addTasksToTaskList(TaskList taskList, String[] taskBreakdown, boolean isTaskDone, String taskDescription) {
        try {
            // An empty task that will be override later
            Task task = new Task("dummy task");

            if (isTaskTypeOf(TODO_IDENTIFIER, taskBreakdown)) {
                task = new Todo(taskDescription);
            } else if (isTaskTypeOf(DEADLINE_IDENTIFIER, taskBreakdown)) {
                task = new Deadline(taskDescription, taskBreakdown[TASK_DATE_INDEX]);
            } else if (isTaskTypeOf(EVENT_IDENTIFIER, taskBreakdown)) {
                task = new Event(taskDescription, taskBreakdown[TASK_DATE_INDEX]);
            }
            if (isTaskDone) {
                task.setDone();
            }
            taskList.addTask(task);
        } catch (EmptyTaskDukeException e) {
            Ui.printError(e.getMessage());
        }
    }

    private static String[] getTaskBreakdownInArray(String taskString) {
        return taskString.split("\\|");
    }

    private static boolean isTaskTypeOf(String t, String[] taskBreakdown) {
        return taskBreakdown[TASK_TYPE_INDEX].equals(t);
    }
}
