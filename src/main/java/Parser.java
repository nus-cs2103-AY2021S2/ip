import javafx.application.Platform;

import java.util.List;

/**
 * Parses commands and executes them.
 */
public class Parser {

    TaskList taskList;
    Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses user input into commands that are executed.
     *
     * @param input User input.
     * @return flag to stop the bot
     * @throws DukeException If input parsing fails.
     */
    public String parse(String input) {
        try {
            if (input.equals("list")) {
                return taskList.toString();
            } else if (input.startsWith("find")) {
                String searchTerm = input.split(" ")[1];
                TaskList findResult = taskList.findTasks(searchTerm);
                return findResult.toString();
            } else if (input.startsWith("done")) {
                int itemNo = Integer.parseInt(input.split(" ")[1]);
                Task selected = taskList.markAsDone(itemNo - 1);
                return ui.displayDone(selected);
            } else if (input.startsWith("delete")) {
                int itemNo = Integer.parseInt(input.split(" ")[1]);
                Task selected = taskList.delete(itemNo - 1);
                return ui.displayDeleted(selected);
            } else if (input.startsWith("todo")) {
                Task task = Todo.parse(input);
                taskList.add(task);
                return ui.displayAdded(task);
            } else if (input.startsWith("deadline")) {
                Task task = Deadline.parse(input);
                taskList.add(task);
                return ui.displayAdded(task);
            } else if (input.startsWith("event")) {
                Task task = Event.parse(input);
                taskList.add(task);
                return ui.displayAdded(task);
            } else if (input.equals("bye")) {
                Platform.exit();
            } else {
                String error = "Sorry! I don't know what that means.";
                throw new DukeException(error);
            }
        } catch (DukeException e) {
            return ui.displayError(e.getMessage());
        }
        return "";
    }
}