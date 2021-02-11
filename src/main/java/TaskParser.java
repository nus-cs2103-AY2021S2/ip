import javafx.application.Platform;

public class TaskParser {

    /**
     * Process the user input to make sense for the system.
     *
     * @param nextInput The user input.
     * @param tasks     The Task Arraylist containing user tasks in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return Return the correct logic and GUI output.
     */
    public String processInput(String nextInput, TaskList tasks, Ui ui) {
        String command = nextInput.contains(" ") ? nextInput.split(" ")[0] : nextInput;
        try {
            switch (command.toLowerCase()) {
            case "todo":
                return todo(nextInput, tasks, ui);
            case "deadline":
                return deadline(nextInput, tasks, ui);
            case "event":
                return event(nextInput, tasks, ui);
            case "done":
                return done(nextInput, tasks, ui);
            case "delete":
                return delete(nextInput, tasks, ui);
            case "list":
                return list(tasks, ui);
            case "find":
                return find(nextInput, tasks, ui);
            case "exit":
                return exitCommand();
            default:
                return wrongCommand();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a Todo task.
     *
     * @param nextInput The description of the task.
     * @param tasks     The Task Arraylist containing user tasks in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public String todo(String nextInput, TaskList tasks, Ui ui) throws DukeException {
        if (nextInput.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String command = nextInput.substring(5);
        tasks.add(new Todo(command, false));
        return ui.showTodoMsg(tasks);
    }

    /**
     * Adds a Deadline task.
     *
     * @param nextInput The description of the task.
     * @param tasks     The Task Arraylist containing user tasks in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public String deadline(String nextInput, TaskList tasks, Ui ui) throws DukeException {
        if (nextInput.length() < 10) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (!nextInput.contains("/")) {
            throw new DukeException("OOPS!!! The date information of a deadline cannot be empty.");
        }
        String command = nextInput.substring(9, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Deadline(command, dateInfo, false, false));
        return ui.showDeadlineMsg(tasks);
    }

    /**
     * Adds a Event task.
     *
     * @param nextInput The description of the task.
     * @param tasks     The Task Arraylist containing user tasks in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the command given is invalid.
     */
    public String event(String nextInput, TaskList tasks, Ui ui) throws DukeException {
        if (nextInput.length() < 7) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (!nextInput.contains("/")) {
            throw new DukeException("OOPS!!! The date information of an event cannot be empty.");
        }
        String command = nextInput.substring(6, nextInput.indexOf("/") - 1);
        String dateInfo = nextInput.substring(nextInput.indexOf("/") + 4);
        tasks.add(new Event(command, dateInfo, false, false));
        return ui.showEventMsg(tasks);
    }

    /**
     * Mark the task in the given task number as done.
     *
     * @param command The command given by user input.
     * @param tasks   The Task Arraylist containing user tasks in sequence.
     * @param ui      UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public String done(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > tasks.getSize() || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        tasks.getTaskList().get(itemNum - 1).makeDone();
        assert tasks.getTaskList().get(itemNum - 1).isDone : tasks.getTaskList().get(itemNum - 1).description;
        return ui.showDoneMsg(tasks, itemNum);
    }

    /**
     * Delete the task corresponding to the number input by the user.
     *
     * @param command The command given by user input.
     * @param tasks   The Task Arraylist containing user tasks in sequence.
     * @param ui      UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the number given is out of range.
     */
    public String delete(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() < 8) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }
        String[] commandToWords = command.split(" ");
        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > tasks.getSize() || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        String taskRemoved = tasks.getTaskList().get(itemNum - 1).toString();
        tasks.delete(itemNum - 1);
        return ui.showDeleteTaskMsg(taskRemoved, tasks.getSize());
    }

    /**
     * List out all user inputs in sequence.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param ui    UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     */
    public String list(TaskList tasks, Ui ui) {
        return ui.showTaskListMsg(tasks);
    }

    /**
     * Find tasks matching the keyword in the command given by the user.
     *
     * @param command The command given by user input.
     * @param tasks   The Task Arraylist containing user tasks in sequence.
     * @param ui      UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String find(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() < 6) {
            throw new DukeException("OOPS!!! The keyword cannot be empty.");
        }
        String keyword = command.split(" ")[1];
        return ui.showFindMsg(keyword, tasks);
    }

    /**
     * Tells the user that the input given is invalid.
     *
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String wrongCommand() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Terminate the GUI when the user decides to.
     *
     * @return Nothing since this command will close the GUI.
     */
    public String exitCommand() {
        Platform.exit();
        return null;
    }
}
