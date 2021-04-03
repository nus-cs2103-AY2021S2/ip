package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exception.DukeException;


/**
 * Contains the task list and functions related to modifying it.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor for the tasklist class object.
     *
     * @param tasks parsed tasks from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets all tasks of the tasklist.
     * @return all tasks of the tasklist.
     */
    public static ArrayList<Task> getAllTasks() {
        return tasks;
    }


    /**
     * Verifies if the given taskIndex is valid.
     * One possible error is handled here. It is:
     *      1. taskIndex is out of bound;
     *
     * @param taskIndex taskIndex from user input after being parsed.
     * @return index in int if it is valid.
     * @throws DukeException if invalid index is provided.
     */
    private static int verifyTaskIndex(int taskIndex) throws DukeException {
        int index = taskIndex - 1;

        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Task with the given index does not exist.");
        }

        return index;
    }

    /**
     * Removes the task with the given index and print the confirmation message.
     *
     * @param taskIndex taskIndex from user input.
     * @return the corresponding results to be printed to users.
     * @throws DukeException when an invalid taskIndex is entered.
     */
    public static String[] deleteTask(int taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.remove(index);

        String[] successMessage = new String[] {
            "On your command! I have removed this task:",
            "  " + task.toString(),
            "Now you have " + tasks.size() + " "
                + (tasks.size() > 1 ? "tasks" : "task")
                + " in the list."
        };

        return successMessage;
    }

    /**
     * Completes the task with the given index and print the confirmation message.
     * One possible error is handled. Namely, it is:
     *      1. the task has been completed;
     *
     * @param taskIndex taskIndex from user input.
     * @return the corresponding results to be printed to users.
     * @throws DukeException when an invalid taskIndex is entered.
     */
    public static String[] completeTask(int taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.get(index);
        boolean isDone = task.getIsDone();
        if (isDone) {
            throw new DukeException("Task with the given index has been completed.");
        } else {
            task.markAsDone();
        }

        String[] successMessage = new String[] {
            "Wonderful! You have completed this task:",
            "  " + task.toString()
        };
        return successMessage;
    }

    /**
     * Adds the task given to the task list.
     *
     * @param newTask a new task to be added.
     * @return the corresponding results to be printed to users.
     */
    public static String[] addTask(Task newTask) {
        tasks.add(newTask);

        String[] successMessage = new String[] {
            "Roger that! Added new task:",
            " " + newTask.toString(),
            "Now you have " + tasks.size() + " "
                + (tasks.size() > 1 ? "tasks" : "task")
                + " in the list."
        };
        return successMessage;
    }

    /**
     * Gets the information of tasks in the task list.
     *
     * @return the information of the tasks present.
     */
    public static String[] getAllTasksInfo() {
        String[] allTasksInfo;

        if (tasks.isEmpty()) {
            allTasksInfo = new String[] {"Hi! Your todo list is currently empty."};
        } else {
            allTasksInfo = new String[tasks.size() + 1];
            allTasksInfo[0] = "Hi! This is your todo list:";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                allTasksInfo[i + 1] = " " + (i + 1) + "." + task.toString();
            }
        }

        return allTasksInfo;
    }

    /**
     * Gets all tasks containing the given keyword.
     *
     * @param keyword user input, a keyword of task description to be searched.
     * @return the information of the tasks containing the given keyword in array for printing.
     */
    public static String[] getTasksFound(String keyword) {
        ArrayList<String> matchedTasksInfo = new ArrayList<>();
        String[] matchedTasksInfoInArray;

        findTasks(keyword, matchedTasksInfo);
        matchedTasksInfoInArray = convertTasksFoundToArray(matchedTasksInfo);

        return matchedTasksInfoInArray;
    }

    private static String[] convertTasksFoundToArray(ArrayList<String> matchedTasksInfo) {
        boolean noMatchedTasksFound = (matchedTasksInfo.size() == 1);

        if (noMatchedTasksFound) {
            matchedTasksInfo.clear();
            matchedTasksInfo.add("Sorry, no relevant tasks found:(");
        }

        return matchedTasksInfo.toArray(new String[0]);
    }

    private static void findTasks(String keyword, ArrayList<String> matchedTasksInfo) {
        matchedTasksInfo.add("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int index = i + 1;
            if (task.toString().contains(keyword)) {
                String description = " " + index + "." + task.toString();
                matchedTasksInfo.add(description);
            }
        }
    }

    /**
     * Updates the deadline object at the given index with the new by date.
     *
     * @param taskIndex the index of the deadline object to be updated.
     * @param newDate new date to update.
     * @return the successful message if update is successful.
     * @throws DukeException when the task at the given index is not an deadline object.
     */
    public static String[] updateTaskDate(int taskIndex, LocalDate newDate) throws DukeException {
        int verifiedTaskIndex = verifyTaskIndex(taskIndex);
        Task taskToBeUpdated = tasks.get(verifiedTaskIndex);
        taskToBeUpdated.updateDate(newDate);

        String[] successMessage = new String[] {
            "Wonderful! You have updated the by date:",
            "  " + taskToBeUpdated.toString()
        };

        return successMessage;
    }
}
