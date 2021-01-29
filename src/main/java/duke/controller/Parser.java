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
        this.ui = ui;
    }

    /**
     * Prints out the current list of Tasks.
     *
     * @param taskList List of Tasks.
     */
    public void handleList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:\n" + taskList.toString());
    }

    /**
     * Marks a Task as done given the command. It is assumed that the command is correct,
     * in the sense that the command the method will receive is 'done' and not something
     * else.
     *
     * @param input User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public void handleDone(String input, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            taskList.markAsDone(index);
            System.out.println("Nice! I've marked this java.duke.controller.task as done:");
            System.out.println(taskList.getTaskAtIndex(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "You have " + taskList.getNumberOfTasks()
                            + " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            throw new DukeException("The input must be a positive integer!");
        }
    }

    /**
     * Adds a ToDo Task into the list.
     *
     * @param input User input
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public void handleTodo(String input, TaskList taskList) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String task = input.substring(input.indexOf(" ") + 1);
        Task temp = new ToDo(task);
        taskList.addTask(temp);
        this.ui.printOnListChange(
                "Got it. I have added the following java.duke.controller.task:",
                temp,
                taskList.getNumberOfTasks());
    }

    /**
     * Adds either a Deadline Task or Event Task based on the command.
     *
     * @param command Command.
     * @param input User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public void handleTasksWithTime(String command, String input, TaskList taskList) throws DukeException {
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
            this.ui.printOnListChange(
                    "Got it. I have added the following java.duke.controller.task:",
                    temp,
                    taskList.getNumberOfTasks());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The timing of the java.duke.controller.task is not included. Please check your input.");
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please input a date with correct format (yyyy-mm-dd).");
        }
    }

    /**
     * Deletes the Task at the specified index (indexed from 1).
     *
     * @param input User input.
     * @param taskList List of Tasks.
     * @throws DukeException If the user input is incorrect.
     */
    public void handleDelete(String input, TaskList taskList) throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            this.ui.printOnListChange(
                    "Noted. I have removed the following java.duke.controller.task:",
                    taskList.deleteTask(index),
                    taskList.getNumberOfTasks()
            );
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "You have " + taskList.getNumberOfTasks() +
                            " tasks in your list. Please check your input.");
        } catch (NumberFormatException e) {
            throw new DukeException("The input must be a positive integer!");
        }
    }

    public void handleFind(String input, TaskList taskList) throws DukeException {
        if(input.split(" ").length < 2) {
            throw new DukeException("Please provide a keyword.");
        }
        String keyword = input.substring(input.indexOf(" "));
        System.out.println("Here are the tasks that I can find:");
        System.out.println(taskList.find(keyword).toString());
    }
}
