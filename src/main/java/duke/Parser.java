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
     * Passing the command to the relevant methods.
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
            default:
                Ui.throwIllegalArgumentEx(command);
                break;
            }
        } catch (DukeException | IllegalArgumentException e) {
            Ui.printErr(e.getMessage());
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
            Task currTask = tasks.get(taskNum);
            currTask.markDone();
            System.out.println("Swee chai. It's done.\n" + currTask);
            if (storage != null) {
                storage.updateTaskDone(taskNum);
            }
        } catch (NumberFormatException e) {
            Ui.printErr("Please enter a number from 1 to " + tasks.size() + " after done!");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                Ui.printErr("Your list is empty!");
            } else {
                Ui.printErr("Please enter a number from 1 to " + tasks.size() + " after done!");
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
            Task currTask = tasks.remove(taskNum);
            System.out.println("See la. It's deleted.\n" + currTask +
                    "\nYou currently have " + tasks.size() + " task(s) in the list.");
            if (storage != null) {
                storage.deleteTask(taskNum);
            }
        } catch (NumberFormatException e) {
            Ui.printErr("Please enter a number from 1 to " + tasks.size() + " after delete!");
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                Ui.printErr("Your list is empty!");
            } else {
                Ui.printErr("Please enter a number from 1 to " + tasks.size() + " after delete!");
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
        System.out.println("Your task has been added: " + task +
                "\nYou currently have " + tasks.size() + " task(s) in the list.");
    }
}
