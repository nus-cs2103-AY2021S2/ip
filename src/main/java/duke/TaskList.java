package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with an ArrayList of tasks.
     *
     * @param taskCommands ArrayList of Strings representing task commands.
     */
    public TaskList(ArrayList<String> taskCommands) {
        try {
            tasks = initialiseTasks(taskCommands);
        } catch (EmptyDescriptionException e) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Creates a new TaskList object with an empty lisk of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    private ArrayList<Task> initialiseTasks(ArrayList<String> taskCommands) throws EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String taskCommand : taskCommands) {
            Task task;
            if (taskCommand.charAt(0) == '#') {
                taskCommand = taskCommand.substring(1);
                task = createTask(taskCommand);
                task.setDone();
            } else {
                task = createTask(taskCommand);
            }
            tasks.add(task);
        }
        return tasks;
    }

    private Task createTask(String taskCommand) throws EmptyDescriptionException {
        String[] arr = taskCommand.split(" ", 2);
        if (arr.length == 1) {
            throw new EmptyDescriptionException();
        }
        String type = arr[0];
        String rest = arr[1];
        Task task;
        if (type.equals("todo")) {
            task = new Todo(rest);
        } else if (type.equals("deadline")) {
            String[] temp = rest.split(" /by ");
            String description = temp[0];
            String by = temp[1];
            task = new Deadline(description, by);
        } else {
            String[] temp = rest.split(" /at ");
            String description = temp[0];
            String at = temp[1];
            task = new Event(description, at);
        }
        return task;
    }

    /**
     * Creates and adds a new task to the list of tasks.
     *
     * @param taskCommand Command entered by the user to create a new task.
     * @throws EmptyDescriptionException If the task does not contain a description.
     */
    public void addTask(String taskCommand) throws EmptyDescriptionException {
        Task task = createTask(taskCommand);
        tasks.add(task);
    }

    /**
     * Deletes the task with taskId from the list of tasks.
     *
     * @param taskId ID of the task.
     */
    public void deleteTask(int taskId) {
        tasks.remove(taskId - 1);
    }

    /**
     * Returns an ArrayList of Strings representing the current tasks in the list of tasks.
     * The tasks are represented in a format that is to be displayed to the user.
     *
     * @return ArrayList of Strings representing the tasks in printable format.
     */
    public ArrayList<String> getPrintableTasks() {
        ArrayList<String> printableTasks = new ArrayList<>();
        int id = 1;
        for (Task task : tasks) {
            printableTasks.add(id + ". " + task);
            id++;
        }
        return printableTasks;
    }

    /**
     * Returns an ArrayList of Strings representing the current tasks in the list of tasks whose
     * description contains the key word given by the user.
     * The tasks are represented in a format that is to be displayed to the user.
     *
     * @param keyword Keyword
     * @return ArrayList of Strings representing the tasks in printable format.
     */
    public ArrayList<String> getPrintableTasksWithKeyword(String keyword) {
        ArrayList<String> printableTasks = new ArrayList<>();
        int id = 1;
        for (Task task : tasks) {
            if (task.descriptionContains(keyword)) {
                printableTasks.add(id + ". " + task);
                id++;
            }
        }
        return printableTasks;
    }

    /**
     * Returns an ArrayList of Strings representing the current tasks in the list of tasks.
     * The tasks are represented in a format that is to be written to the hard disk.
     *
     * @return ArrayList of Strings representing the tasks in writable format.
     */
    public ArrayList<String> getWritableTasks() {
        ArrayList<String> writableTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                writableTasks.add("#" + task.getTaskCommand());
            } else {
                writableTasks.add(task.getTaskCommand());
            }
        }
        return writableTasks;
    }

    /**
     * Deletes the task with taskId from the list of tasks.
     *
     * @param taskId ID of the task.
     */
    public void markTaskAsDone(int taskId) {
        tasks.get(taskId - 1).setDone();
    }

    /**
     * Returns the string representation of the task with taskId in the list of tasks.
     *
     * @param taskId ID of the task.
     * @return String representation of the task.
     */
    public String getTaskString(int taskId) {
        return tasks.get(taskId - 1).toString();
    }

    /**
     * Returns the number of tasks in the list of tasks.
     *
     * @return Number of tasks.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }
}
