package duke;

/**
 * Parser for Duke commands, with access to its task list, and storage.
 */
public class Parser {
    private TaskList tasks;
    private Storage storage;

    /**
     * Creates a Parser object for the Duke instance.
     *
     * @param tasks   The current TaskList used.
     * @param storage The current Storage used.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Passes the command to the relevant methods.
     *
     * @param command A string of the command to be carried out.
     */
    public void parseCommand(String command) {
        command = command.trim();
        try {
            switch (command.split(" ")[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                Ui.printList(tasks);
                break;
            case "done":
                handleDone(command);
                break;
            case "delete":
                handleDelete(command);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                Task newTask = Task.dispatchTaskCreation(command);
                addTask(newTask);
                if (storage != null) {
                    storage.storeTask(command);
                }
                break;
            case "find":
                handleFind(command);
                break;
            default:
                Ui.throwIllegalArgumentEx(command);
                break;
            }
        } catch (DukeException | IllegalArgumentException e) {
            Ui.printError(e.getMessage());
        }
    }

    /**
     * Finds and marks task specified in the command string after calling done.
     * Handles exceptions that include index out of bounds and number format.
     * Saves to storage.
     *
     * @param command "done {task number}"
     */
    private void handleDone(String command) {
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = tasks.get(taskNum);
            currentTask.markDone();
            System.out.println("Swee chai. It's done.\n" + currentTask);
            if (storage != null) {
                storage.updateTaskDone(taskNum);
            }
        } catch (NumberFormatException e) {
            Ui.printError("Please enter a number from 1 to " + tasks.size() + " after done!");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                Ui.printError("Your list is empty!");
            } else {
                Ui.printError("Please enter a number from 1 to " + tasks.size() + " after done!");
            }
        }
    }

    /**
     * Deletes the task in the list. Updates storage.
     * Handles number format and index out of bounds exceptions.
     *
     * @param command "delete {task number}"
     */
    private void handleDelete(String command) {
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = tasks.remove(taskNum);
            System.out.println("See la. It's deleted.\n" + currentTask
                    + "\nYou currently have " + tasks.size() + " task(s) in the list.");
            if (storage != null) {
                storage.deleteTask(taskNum);
            }
        } catch (NumberFormatException e) {
            Ui.printError("Please enter a number from 1 to " + tasks.size() + " after delete!");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                Ui.printError("Your list is empty!");
            } else {
                Ui.printError("Please enter a number from 1 to " + tasks.size() + " after delete!");
            }
        }
    }

    /**
     * Adds specified task into task list and prints user feedback.
     *
     * @param task Task to be added.
     */
    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Your task has been added: " + task
                + "\nYou currently have " + tasks.size() + " task(s) in the list.");
    }

    private void handleFind(String command) {
        try {
            String query = command.split(" ", 2)[1].trim();
            StringBuffer reply = new StringBuffer();
            boolean isFound = false;
            for (int i = 1; i <= tasks.size(); i++) {
                if (tasks.get(i - 1).toString().contains(query)) {
                    isFound = true;
                    reply.append(i + "." + tasks.get(i - 1) + "\n");
                }
            }
            if (isFound) {
                reply.insert(0, "Here are the matching tasks in your list:\n");
                System.out.print(reply);
            } else {
                System.out.println("There are no tasks that match \"" + query + "\"!");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printError("Please type in your query after find!");
        }
    }
}
