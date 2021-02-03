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
     * @return response to the command.
     */
    public String parseCommand(String command) {
        String response = "";
        command = command.trim();
        try {
            switch (command.split(" ")[0]) {
            case "bye":
                response = "Bye. Hope to see you again soon!";
                break;
            case "list":
                response = Ui.printList(tasks);
                break;
            case "done":
                response = handleDone(command);
                break;
            case "delete":
                response = handleDelete(command);
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                Task newTask = Task.dispatchTaskCreation(command);
                response = addTask(newTask);
                if (storage != null) {
                    storage.storeTask(command);
                }
                break;
            case "find":
                response = handleFind(command);
                break;
            default:
                Ui.throwIllegalArgumentEx(command);
                break;
            }
        } catch (DukeException | IllegalArgumentException e) {
            response = e.getMessage();
        } finally {
            return response;
        }
    }

    /**
     * Finds and marks task specified in the command string after calling done.
     * Handles exceptions that include index out of bounds and number format.
     * Saves to storage.
     *
     * @param command "done {task number}".
     * @return feedback whether task is marked done successfully or not.
     */
    private String handleDone(String command) {
        String reply = "";
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = tasks.get(taskNum);
            currentTask.markDone();
            reply = "Noice. It's done. \n" + currentTask;
            if (storage != null) {
                storage.updateTaskDone(taskNum);
            }
        } catch (NumberFormatException e) {
            reply = "Please enter a number from 1 to " + tasks.size() + " after done!";
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                reply = "Your list is empty!";
            } else {
                reply = "Please enter a number from 1 to " + tasks.size() + " after done!";
            }
        }
        return reply;
    }

    /**
     * Deletes the task in the list. Updates storage.
     * Handles number format and index out of bounds exceptions.
     *
     * @param command "delete {task number}".
     * @return feedback whether the task is deleted successfully.
     */
    private String handleDelete(String command) {
        String reply = "";
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]) - 1;
            Task currentTask = tasks.remove(taskNum);
            reply = "See la. It's deleted. \n" + currentTask
                    + "\nYou currently have " + tasks.size() + " task(s) in the list.";
            if (storage != null) {
                storage.deleteTask(taskNum);
            }
        } catch (NumberFormatException e) {
            reply = "Please enter a number from 1 to " + tasks.size() + " after delete!";
        } catch (IndexOutOfBoundsException e) {
            if (tasks.size() == 0) {
                reply = "Your list is empty!";
            } else {
                reply = "Please enter a number from 1 to " + tasks.size() + " after delete!";
            }
        }
        return reply;
    }

    /**
     * Adds specified task into task list and returns user feedback.
     *
     * @param task Task to be added.
     * @return feedback for adding task successfully.
     */
    private String addTask(Task task) {
        tasks.add(task);
        return "Your task has been added: " + task
                + "\nYou currently have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Loops through the existing TaskList and finds all tasks that match the given argument.
     *
     * @param command "find {string to match}".
     * @return string of tasks matching argument.
     */
    private String handleFind(String command) {
        StringBuffer reply = new StringBuffer();
        try {
            String query = command.split(" ", 2)[1].trim();
            boolean isFound = false;
            for (int i = 1; i <= tasks.size(); i++) {
                if (tasks.get(i - 1).toString().contains(query)) {
                    isFound = true;
                    reply.append(i + "." + tasks.get(i - 1) + "\n");
                }
            }
            if (isFound) {
                reply.insert(0, "Here are the matching tasks in your list:\n");
            } else {
                return "There are no tasks that match \"" + query + "\"!";
            }
        } catch (IndexOutOfBoundsException e) {
            return "Please type in your query after find!";
        }
        return reply.toString();
    }
}
