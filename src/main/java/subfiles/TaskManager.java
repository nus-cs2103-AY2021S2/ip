package main.java.subfiles;

import java.util.ArrayList;

import main.java.exceptions.EmptyDescriptionException;
import main.java.exceptions.EmptyTimeException;
import main.java.exceptions.InvalidInputException;
import main.java.exceptions.ListOutOfBoundsException;

/**
 * The TaskManager class contains a list of tasks created by
 * user input, and allows the user to add, print, or delete
 * tasks, as well as to mark a task in the list as done.
 *
 * @author  arsatis
 * @version 1.0
 * @since   2021-01-19
 */
public class TaskManager {
    /** List of tasks created by user input */
    private ArrayList<Task> tasks;

    /**
     * Default constructor for the TaskManager class.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a to-do to the list of tasks.
     *
     * @param s User input triggering the addition of a to-do
     *          to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided
     *                                   for the to-do.
     */
    private void addTodo(String s)
            throws EmptyDescriptionException {
        try {
            s = s.substring(5);
            tasks.add(new ToDo(s));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param s User input triggering the addition of a deadline
     *          to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided
     *                                   for the deadline.
     * @throws EmptyTimeException If no date or time is specified for
     *                            the deadline.
     */
    private void addDeadline(String s)
            throws EmptyDescriptionException, EmptyTimeException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(9, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Deadline(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("deadline");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTimeException("deadline");
        }
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param s User input triggering the addition of an event
     *          to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided
     *                                   for the event.
     * @throws EmptyTimeException If no date or time is specified for
     *                            the event.
     */
    private void addEvent(String s)
            throws EmptyDescriptionException, EmptyTimeException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(6, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Event(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("event");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTimeException("event");
        }
    }

    /**
     * Adds a task to the list of tasks. Calls either addTodo,
     * addDeadline, or addEvent, depending on the type of task
     * specified by the user.
     *
     * @param s User input triggering the addition of a task
     *          to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided
     *                                   for the task.
     * @throws EmptyTimeException If no date or time is specified for
     *                            the task, which is either a deadline
     *                            or an event.
     * @throws InvalidInputException If the task is neither a to-do, a
     *                               deadline, nor an event.
     */
    public void addTask(String s)
            throws EmptyDescriptionException, EmptyTimeException, InvalidInputException {
        String command = s.split(" ", 2)[0];

        switch (command) {
        case "todo":
            addTodo(s);
            break;
        case "deadline":
            addDeadline(s);
            break;
        case "event":
            addEvent(s);
            break;
        default:
            throw new InvalidInputException();
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the list of tasks added by the user till this point,
     * based on the order they were added by the user.
     */
    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + ". " + task.toString());
        }
    }

    /**
     * Marks a task that is specified by the user as done. The
     * user should specify the index of the task in the list
     * which he or she intends to mark as done.
     *
     * @param s Index of the task to be marked as done
     *          in the list of tasks, in String format.
     * @throws InvalidInputException If the user provided a non-integer index
     *                               in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is
     *                                  not in the list.
     */
    public void markDone(String s)
            throws InvalidInputException, ListOutOfBoundsException {
        try {
            int index = Integer.parseInt(s) - 1;
            tasks.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index).toString());
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    /**
     * Deletes a task that is specified by the user from the
     * list of tasks. The user should specify the index of the
     * task in the list which he or she intends to delete.
     *
     * @param s Index of the task to be marked as done
     *          in the list of tasks, in String format.
     * @throws InvalidInputException If the user provided a non-integer index
     *                               in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is
     *                                  not in the list.
     */
    public void deleteTask(String s) throws InvalidInputException, ListOutOfBoundsException {
        try {
            int index = Integer.parseInt(s) - 1;
            Task t = tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

}
