package flamingo;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    protected static int numTasks;
    protected ArrayList<Task> tasks;

    /**
     * Creates a task list given existing ArrayList of tasks.
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        numTasks = tasks.size();
    }

    /**
     * Creates a new empty Task List.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        numTasks = 0;
    }

    public static int getNumTasks() {
        return numTasks;
    }

    /**
     * Prints out the tasks in the ArrayList.
     */
    public String listTasks() {
        String list = "";

        for (int i = 1; i < numTasks + 1; i++) {
            list += (i + ". " + tasks.get(i - 1).toString() + "\n");
        }

        return list;
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param task Task from user input.
     */
    public String addTask(Task task) {
        tasks.add(numTasks, task);
        numTasks++;
        return "Okay, I've added this task:\n" + task.toString()
                + "\nYou now have a total of " + numTasks + " tasks.";
    }

    /**
     * Marks a task as done.
     *
     * @param num Number representing task in the list.
     */
    public String markAsDone(int num) {
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();

        return "Okay, this task has been marked as done:\n"
                + num + ". " + currentTask.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param num Number representing task in the list.
     */
    public String deleteTask(int num) {
        String taskToDelete = tasks.get(num - 1).toString();
        tasks.remove(num - 1);
        numTasks--;

        return "Okay, I've deleted this task:\n" + taskToDelete
                + "\nYou now have a total of " + numTasks + " tasks.";
    }

    /**
     * Finds matching results from a given keyword.
     *
     * @param keyword Keyword from user input.
     */
    public String findTask(String keyword) {
        boolean hasMatch = false;
        ArrayList<Task> matches = new ArrayList<>();
        ArrayList<Integer> taskNumber = new ArrayList<>();
        int num = 1;

        for (Task t : tasks) {
            String temp = t.toString();

            if (temp.contains(keyword)) {
                hasMatch = true;
                matches.add(t);
                taskNumber.add(num);
            }
            num++;
        }

        if (hasMatch) {
            String listOfMatches = "";
            for (int j = 0; j < matches.size(); j++) {
                listOfMatches += taskNumber.get(j) + ". " + matches.get(j) + "\n";
            }
            return "Bingo Flamingo! I've found these matches:\n"
                    + listOfMatches;
        } else {
            return "Oh no Flamingo! No matches were found.";
        }
    }
}
