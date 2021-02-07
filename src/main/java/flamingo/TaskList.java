package flamingo;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {
    protected static int numTasks;
    protected static ArrayList<Task> tasks;

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
        return Ui.addTask(task, numTasks);
    }

    /**
     * Marks a task as done.
     *
     * @param num Number representing task in the list.
     */
    public String markAsDone(int num) {
        Task currentTask = tasks.get(num - 1);
        currentTask.markAsDone();
        return Ui.markAsDone(num, currentTask);
    }

    /**
     * Deletes a task from the list.
     *
     * @param num Number representing task in the list.
     */
    public String deleteTask(int num) {
        String taskToDelete = getTaskToDelete(num);
        tasks.remove(num - 1);
        numTasks--;
        return Ui.deleteTask(taskToDelete, numTasks);
    }

    private String getTaskToDelete(int num) {
        return tasks.get(num - 1).toString();
    }

    /**
     * Finds matching results from a given keyword.
     *
     * @param keyword Keyword from user input.
     */
    public String findTask(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        boolean hasMatch = findMatches(keyword, matches, taskNumbers);

        if (hasMatch) {
            return Ui.printOutMatches(matches, taskNumbers);
        } else {
            return Ui.noMatchesFound();
        }
    }

    /**
     * Finds matches in the task list given a keyword.
     *
     * @param keyword Keyword from user input.
     * @param matches ArrayList of matches found.
     * @param taskNumbers ArrayList of taskNumbers found.
     * @return True if a match is found, false if a match is not found.
     */
    private boolean findMatches(String keyword, ArrayList<Task> matches,
                                ArrayList<Integer> taskNumbers) {
        boolean isMatchFound = false;
        int taskNum = 1;

        for (Task t : tasks) {
            String temp = t.toString();

            if (temp.contains(keyword)) {
                isMatchFound = true;
                matches.add(t);
                taskNumbers.add(taskNum);
            }
            taskNum++;
        }
        return isMatchFound;
    }

    /**
     * Archives a task. The task is moved from list of tasks to the archive of tasks.
     *
     * @param num Task number of the task to be archived.
     * @return String confirming a task has been archived.
     */
    public String archiveTask(int num) {
        Task taskToArchive = tasks.get(num - 1);
        String taskString = ArchivedTaskList.addTaskToArchive(taskToArchive);
        tasks.remove(num - 1);
        numTasks--;

        return taskString;
    }

    /**
     * Archives all tasks from the active task list.
     *
     * @return String confirming all tasks are archived.
     */
    public String archiveAllTasks() {
        String taskString = ArchivedTaskList.addAllTasksToArchive(tasks);
        for (int i = 0; i < numTasks; i++) {
            tasks.remove(0);
        }
        numTasks = 0;
        return taskString;
    }
}
