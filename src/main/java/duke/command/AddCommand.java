package duke.command;

import duke.task.Deadlines;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;


/**
 * Sub-class of Command that represents and execute the todo, deadline and event instructions of user.
 */
public class AddCommand extends Command {

    /**
     * Create a AddCommand object for execution.
     *
     * @param instruction user instruction.
     * @param task        task name of the user in String.
     * @param date        date of the user task to be done.
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
     * handle todo command and create a todo task if task is not empty.
     *
     * @param task name of the user task.
     */
    private static String handleToDo(String task) {

        Todo todo = new Todo(task);
        TaskList.addTask(todo);
        System.out.println(Ui.biggerBox(todo));

        return Ui.biggerBox(todo);

    }


    private static String handleDeadline(String task, String date) {


        Deadlines deadlines = new Deadlines(task, date);
        TaskList.addTask(deadlines);
        System.out.println(Ui.biggerBox(deadlines));


        return Ui.biggerBox(deadlines);
    }


    private static String handleEvent(String task, String date) {

        Event event = new Event(task, date);
        TaskList.addTask(event);
        System.out.println(Ui.biggerBox(event));

        return Ui.biggerBox(event);
    }
}
