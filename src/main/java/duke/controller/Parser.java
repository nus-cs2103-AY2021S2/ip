package duke.controller;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;

/**
 * A class to parse user inputs.
 */
public class Parser {
    Ui ui;

    /**
     * Constructs a parser given the application.
     *
     * @param ui Application.
     */
    public Parser(Ui ui) {
        assert ui != null;
        this.ui = ui;
    }

    /**
     * Prints out the current list of Tasks.
     *
     * @param taskList List of Tasks.
     */
    public String handleList(TaskList taskList) {
        assert taskList != null;
        return "Here are the tasks in your list:\n" + taskList.toString() + "\n";
    }

    /**
     * Marks a Task as done given the command. It is assumed that the command is correct,
     * in the sense that the command the method will receive is 'done' and not something
     * else.
     *
     * @param input    User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public String handleDone(String input,
                             TaskList taskList)
            throws DukeException {
        assert (input != null && input.length() > 0);
        assert taskList != null;
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            taskList.markAsDone(index);
            return "Nice! I've marked this task as done:\n" +
                    (taskList.getTaskAtIndex(index));
        } catch (IndexOutOfBoundsException e) {
            return "You have " + taskList.getNumberOfTasks()
                            + " tasks in your list. Please check your input.";
        } catch (NumberFormatException e) {
            throw new DukeException("The input must be a positive integer!");
        }
    }

    /**
     * Adds a ToDo Task into the list.
     *
     * @param input    User input
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public String handleTodo(String input,
                             TaskList taskList)
            throws DukeException {
        assert (input != null && input.length() > 0);
        assert taskList != null;
        if (!input.contains(" ")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String task = input.substring(input.indexOf(" ") + 1);
        Task temp = new ToDo(task);
        taskList.addTask(temp);
        return this.ui.printOnListChange(
                "Got it. I have added the following task:",
                temp,
                taskList.getNumberOfTasks());
    }

    /**
     * Adds either a Deadline Task or Event Task based on the command.
     *
     * @param command  Command.
     * @param input    User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public String handleTasksWithTime(String command,
                                      String input,
                                      TaskList taskList)
            throws DukeException {
        assert (command != null && command.length() > 0);
        assert (input != null && input.length() > 0);
        assert taskList != null;
        try {
            String taskName = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
            Task temp;
            String timing = input.substring(input.indexOf("/") + 4);
            LocalDate date = LocalDate.parse(timing);
            if (command.startsWith("deadline")) {
                temp = new Deadline(taskName, date);
            } else {
                temp = new Event(taskName, date);
            }
            taskList.addTask(temp);
            return this.ui.printOnListChange(
                    "Got it. I have added the following task:",
                    temp,
                    taskList.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The timing of the task is not included. " +
                    "Please check your input. \n");
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please input a date with correct format (yyyy-mm-dd).\n");
        }
    }

    /**
     * Deletes the Task at the specified index (indexed from 1).
     *
     * @param input    User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public String handleDelete(String input,
                               TaskList taskList)
            throws DukeException {
        assert (input != null && input.length() > 0);
        assert taskList != null;
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            return this.ui.printOnListChange(
                    "Noted. I have removed the following task:",
                    taskList.deleteTask(index),
                    taskList.getNumberOfTasks()
            );
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "You have " + taskList.getNumberOfTasks() +
                            " tasks in your list. Please check your input.\n");
        } catch (NumberFormatException e) {
            throw new DukeException("The input must be a positive integer!\n");
        }
    }

    public String handleFind(String input,
                             TaskList taskList)
            throws DukeException {
        assert (input != null && input.length() > 0);
        assert taskList != null;
        if (input.split(" ").length < 2) {
            throw new DukeException("Please provide a keyword.\n");
        }
        String keyword = input.substring(input.indexOf(" ") + 1);
        return "Here are the tasks that I can find:\n" + taskList.find(keyword).toString();
    }

    public String handleRemind(String input,
                               TaskList taskList)
            throws DukeException {
        assert (input != null && input.length() > 0);
        assert taskList != null;
        try {
            if (input.split(" ").length < 2) {
                return "Here are the tasks in the next week:\n"
                        + taskList.remind(7);
            }
            int numberOfDays = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            return "Here are the tasks in the next " + numberOfDays + " days:\n"
                    + taskList.remind(numberOfDays);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a positive integer, " +
                    "so that I know how many days after today I should look into and remind your tasks, " +
                    "you forgetful :P.");
        }
    }
}
