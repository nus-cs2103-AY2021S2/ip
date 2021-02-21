package main.java;

/**
 * A parser that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Identifies the type of user command and calls the relevant method to perform the action.
     * Commands accepted are list, done, delete, todo, deadline and event.
     *
     * @param storage Storage object that deals with loading and saving tasks.
     * @param ui Ui object that deals with interactions with the user.
     * @param tasks TaskList that contains the list of Task objects.
     * @param cmd Command entered by the user.
     * @throws EmptyDescriptionException If the user tries to create a task without a description.
     * @throws InvalidCommandException If the user enters an invalid command.
     * @throws WriteTasksException If an error is encountered when trying to write tasks to the hard disk.
     */
    public void handleInput(Storage storage, Ui ui, TaskList tasks, String cmd) throws EmptyDescriptionException, InvalidCommandException, WriteTasksException {
        if (cmd.equals("list")) {
            ui.printTasks(tasks.getPrintableTasks());
        } else {
            String[] arr = cmd.split(" ", 2);
            switch (arr[0]) {
            case "done":
                int doneId = Integer.parseInt(arr[1]);
                tasks.markTaskAsDone(doneId);
                ui.displayMarkTaskAsDoneMsg(tasks.getTaskString(doneId));
                break;
            case "delete":
                int deleteId = Integer.parseInt(arr[1]);
                ui.displayDeleteTaskMsg(tasks.getTaskString(deleteId), tasks.getNumOfTasks() - 1);
                tasks.deleteTask(deleteId);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                tasks.addTask(cmd);
                int numOfTasks = tasks.getNumOfTasks();
                ui.displayAddTaskMsg(tasks.getTaskString(numOfTasks), numOfTasks);
                break;
            default:
                throw new InvalidCommandException();
            }
            storage.writeTasksToDisk(tasks);
        }
    }
}
