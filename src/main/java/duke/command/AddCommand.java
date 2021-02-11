package duke.command;

import duke.task.Deadlines;
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
     * @param task task name of the user in String.
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
     * @param task name of the user task.
     */
    private static String handleToDo(String task) {
        Todo todo = new Todo(task);
        TaskList.addTask(todo);
        Statistics.updateStatistics(todo);
        return Ui.biggerBox(todo);
    }

    /**
     * This method handles deadline instruction and creates a Deadline task.
     *
     * @param task name of the user task.
     */
    private static String handleDeadline(String task, String date) {
        Deadlines deadlines = new Deadlines(task, date);
        TaskList.addTask(deadlines);
        Statistics.updateStatistics(deadlines);
        return Ui.biggerBox(deadlines);
    }

    /**
     * This method handles event instruction and creates a Event task.
     *
     * @param task name of the user task.
     */
    private static String handleEvent(String task, String date) {
        Event event = new Event(task, date);
        TaskList.addTask(event);
        Statistics.updateStatistics(event);
        return Ui.biggerBox(event);
    }
}
