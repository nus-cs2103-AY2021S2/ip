package duke;

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
    public String handleInput(Storage storage, Ui ui, TaskList tasks, String cmd) throws
        EmptyDescriptionException, InvalidCommandException, WriteTasksException {
        if (cmd.equals("list")) {
            return ui.getListOfTasks(tasks.getPrintableTasks());
        } else if (cmd.equals("bye")) {
            storage.writeTasksToDisk(tasks);
            return ui.getExitMsg();
        } else {
            String[] arr = cmd.split(" ", 2);
            switch (arr[0]) {
            case "find":
                return ui.getListOfFoundTasks(tasks.getPrintableTasksWithKeyword(arr[1]));
            case "done":
                int doneId = Integer.parseInt(arr[1]);
                tasks.markTaskAsDone(doneId);
                return ui.getMarkTaskAsDoneMsg(tasks.getTaskString(doneId));
            case "delete":
                int deleteId = Integer.parseInt(arr[1]);
                String taskString = tasks.getTaskString(deleteId);
                tasks.deleteTask(deleteId);
                return ui.getDeleteTaskMsg(taskString, tasks.getNumOfTasks());
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                tasks.addTask(cmd);
                int numOfTasks = tasks.getNumOfTasks();
                return ui.getAddTaskMsg(tasks.getTaskString(numOfTasks), numOfTasks);
            default:
                throw new InvalidCommandException();
            }
        }
    }
}
