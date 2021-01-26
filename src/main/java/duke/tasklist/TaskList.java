package duke.tasklist;

import duke.main.DukeException;
import duke.main.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static ArrayList<Task> getAllTasks() {
        return tasks;
    }


    /**
     * Verify if the given taskIndex is valid.
     * Three possible errors are handled. Namely, they are:
     *      1. no taskIndex;
     *      2. taskIndex is not an integer;
     *      3. taskIndex is out of bound;
     * @param taskIndex taskIndex from user input, in String.
     * @return index in int if it is valid
     * @throws DukeException if invalid index is provided
     */
    private static int verifyTaskIndex(int taskIndex) throws DukeException {
        int index = taskIndex - 1;

        if (index >= tasks.size()) {
            throw new DukeException("Task with the given index does not exist.");
        }

        return index;
    }

    /**
     * Remove the task with the given index and print the confirmation message.
     * @param taskIndex taskIndex from user input, in String.
     * @throws DukeException when an invalid taskIndex is entered
     */
    public static String[] deleteTask(int taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.remove(index);

        String[] res = new String[] {
                "On your command! I have removed this task:",
                "  " + task.toString(),
                "Now you have " + tasks.size() + " " +
                        (tasks.size() > 1 ? "tasks" : "task") +
                        " in the list."
        };
        return res;
    }

    /**
     * Complete the task with the given index and print the confirmation message.
     *
     * One possible error is handled. Namely, it is:
     *      1. the task has been completed;
     * @param taskIndex taskIndex from user input, in String.
     * @throws DukeException when an invalid taskIndex is entered
     */
    public static String[] completeTask(int taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.get(index);

        if (!task.markAsDone()) {
            throw new DukeException("Task with the given index has been completed.");
        }

        String[] res = new String[] {
                "Wonderful! You have completed this task:",
                "  " + task.toString()
        };
        return res;
    }

    public static String[] addTask(Task newTask) {
        tasks.add(newTask);

        String[] res = new String[] {
                "Roger that! Added new task:",
                " " + newTask.toString(),
                "Now you have " + tasks.size() + " " +
                        (tasks.size() > 1 ? "tasks" : "task") +
                        " in the list."
        };
        return res;
    }

    public static String[] getAllTaskListInfo() {
        String[] res;
        if (tasks.isEmpty()) {
            res = new String[] {"Hi! Your todo list is currently empty."};
        } else {
            res = new String[tasks.size() + 1];
            res[0] = "Hi! This is your todo list:";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                res[i+1] = " " + (i + 1) + "." + task.toString();
            }
        }
        return res;
    }

    /**
     * Finds all tasks containing the given keyword.
     * @param keyword user input, a keyword of task description to be searched
     * @return the information of the tasks containing the given keyword
     */
    public static String[] findTasks(String keyword) {
        ArrayList<String> res = new ArrayList<>();
        res.add("Here are the matching tasks in your list:");

        int i = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                String description = " " + i + "." + task.toString();
                res.add(description);
                i++;
            }
        }

        if (res.size() == 1) {
            res.clear();
            res.add("Sorry, no relevant tasks found:(");
        }

        return res.toArray(new String[0]);
    }
}
