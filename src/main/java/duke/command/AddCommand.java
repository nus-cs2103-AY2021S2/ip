package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.utils.Statistics;


/**
 * Sub-class of Command that represents and execute the todo, deadline and event instructions of user.
 */
public class AddCommand extends Command {

    /**
     * Creates a AddCommand object for execution.
     *
     * @param instruction user instruction.
     * @param task content of the user task.
     * @param date date of the user task to be done.
     */
    public AddCommand(String instruction, String task, String date) {
        super(instruction, task, date, false, command -> {
            if (instruction.equals("todo")) {
                return handleToDo(task);
            } else if (instruction.equals("deadline")) {
                return handleDeadline(task, date);
            } else {
                return handleEvent(task, date);
            }
        });
    }

    /**
     * This method handles todo instruction and creates a Todo task.
     *
     * @param task content of the user task.
     */
    private static String handleToDo(String task) {
        Todo todo = new Todo(task);

        // Update the task list and statistics with the new task.
        TaskList.addTask(todo);
        Statistics.updateStatistics(todo);
        return Ui.createAddResponse(todo);
    }

    /**
     * This method handles deadline instruction and creates a Deadline task.
     *
     * @param task content of the user task.
     */
    private static String handleDeadline(String task, String date) {
        Deadline deadlines = new Deadline(task, date);

        // Update the task list and statistics with the new task.
        TaskList.addTask(deadlines);
        Statistics.updateStatistics(deadlines);
        return Ui.createAddResponse(deadlines);
    }

    /**
     * This method handles event instruction and creates a Event task.
     *
     * @param task content of the user task.
     */
    private static String handleEvent(String task, String date) {
        Event event = new Event(task, date);

        // Update the task list and statistics with the new task.
        TaskList.addTask(event);
        Statistics.updateStatistics(event);
        return Ui.createAddResponse(event);
    }
}
