package duke.subfiles;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DateFormatException;
import duke.exceptions.EmptyDateException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.ListOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The TaskList class contains a list of tasks created by
 * user input, and allows the user to add, print, or delete
 * tasks, as well as to mark a task in the list as done.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class TaskList {
    /** List of tasks created by user input. */
    private ArrayList<Task> tasks;

    /**
     * Default constructor for the TaskList class.
     */
    public TaskList() {
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
     * @throws EmptyDateException If no date is specified for the deadline.
     * @throws DateFormatException If the specified date is incorrectly
     *                             formatted.
     */
    private void addDeadline(String s)
            throws EmptyDescriptionException, EmptyDateException, DateFormatException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(9, sArray[0].length() - 1);
            LocalDate t = LocalDate.parse(sArray[1].substring(3));
            tasks.add(new Deadline(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("deadline");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDateException("deadline");
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param s User input triggering the addition of an event
     *          to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided
     *                                   for the event.
     * @throws EmptyDateException If no date is specified for the event.
     * @throws DateFormatException If the specified date is incorrectly
     *                             formatted.
     */
    private void addEvent(String s)
            throws EmptyDescriptionException, EmptyDateException, DateFormatException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(6, sArray[0].length() - 1);
            LocalDate t = LocalDate.parse(sArray[1].substring(3));
            tasks.add(new Event(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("event");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDateException("event");
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
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
     * @throws EmptyDateException If no date or time is specified for
     *                            the task, which is either a deadline
     *                            or an event.
     * @throws InvalidInputException If the task is neither a to-do, a
     *                               deadline, nor an event.
     * @throws DateFormatException If the specified date is incorrectly
     *                             formatted.
     */
    public String addTask(String s)
            throws EmptyDescriptionException, EmptyDateException, InvalidInputException,
            DateFormatException {
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

        String output = "Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(output);
        return output;
    }

    /**
     * Adds a to-do, deadline, or event, to the list of tasks, based on
     * previously saved data.
     *
     * @param s A line from the user's save data.
     * @throws DateFormatException If the specified date is incorrectly
     *                             formatted.
     */
    public void addTaskFromData(String s) throws DateFormatException {
        String[] sArray = s.split(" \\| ");

        try {
            if (sArray[0].equals("T")) {
                tasks.add(new ToDo(sArray[2]));
            } else if (sArray[0].equals("D")) {
                tasks.add(new Deadline(sArray[2], LocalDate.parse(sArray[3])));
            } else {
                tasks.add(new Event(sArray[2], LocalDate.parse(sArray[3])));
            }

            if (sArray[1].equals("1")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Prints the list of tasks added by the user till this point,
     * based on the order they were added by the user.
     */
    public String printTasks() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            output.append("\n").append(i).append(". ").append(task.toString());
        }

        System.out.println(output);
        return output.toString();
    }

    /**
     * Prints the list of deadlines added by the user till this point,
     * due on the date specified by the user, based on the order they
     * were added by the user.
     *
     * @param date The date specified by the user.
     */
    private String printDeadlinesOnDate(LocalDate date) {
        StringBuilder output;
        ArrayList<Deadline> deadlines = new ArrayList<>();

        for (Task t : tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.getDateAsLocalDate().equals(date)) {
                    deadlines.add(d);
                }
            }
        }

        if (deadlines.size() == 0) {
            output = new StringBuilder("You have no deadlines due on " + date.toString() + ".");
        } else {
            int i = 1;
            output = new StringBuilder("Here are the deadlines due on " + date.toString() + ":");
            for (Deadline d : deadlines) {
                output.append("\n").append(i).append(". ").append(d.toString());
                i++;
            }
        }

        System.out.println(output);
        return output.toString();
    }

    /**
     * Prints the list of events added by the user till this point,
     * happening on the date specified by the user, based on the order
     * they were added by the user.
     *
     * @param date The date specified by the user.
     */
    private String printEventsOnDate(LocalDate date) {
        StringBuilder output;
        ArrayList<Event> events = new ArrayList<>();

        for (Task t : tasks) {
            if (t instanceof Event) {
                Event e = (Event) t;
                if (e.getDateAsLocalDate().equals(date)) {
                    events.add(e);
                }
            }
        }

        if (events.size() == 0) {
            output = new StringBuilder("You have no events due on " + date.toString() + ".");
        } else {
            int i = 1;
            output = new StringBuilder("Here are the events due on " + date.toString() + ":");
            for (Event e : events) {
                output.append("\n").append(i).append(". ").append(e.toString());
                i++;
            }
        }

        System.out.println(output);
        return output.toString();
    }

    /**
     * Prints the list of deadlines and events added by the user till this point,
     * due or happening on the date specified by the user, based on the order
     * they were added by the user.
     *
     * @param s The date specified by the user.
     * @throws DateFormatException If the specified date is incorrectly
     *                             formatted.
     */
    public String printTasksOnDate(String s) throws DateFormatException {
        try {
            LocalDate date = LocalDate.parse(s);
            String outputUpper = printDeadlinesOnDate(date);
            String outputLower = printEventsOnDate(date);
            return outputUpper + "\n" + outputLower;
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Prints the list of tasks added by the user till this point,
     * containing the keyword specified by the user, based on the order
     * they were added by the user.
     *
     * @param s User input containing the keyword.
     */
    public String findTasksWithKeyword(String s) {
        String keyword = s.split(" ", 2)[1].toLowerCase();
        StringBuilder output;
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getName().toLowerCase().contains(keyword)) {
                matchingTasks.add(t);
            }
        }

        if (matchingTasks.size() == 0) {
            output = new StringBuilder("You have no matching tasks in your list.");
        } else {
            int i = 1;
            output = new StringBuilder("Here are the matching tasks in your list:");
            for (Task t : matchingTasks) {
                output.append("\n").append(i).append(". ").append(t.toString());
                i++;
            }
        }

        System.out.println(output);
        return output.toString();
    }

    /**
     * Marks a task that is specified by the user as done. The
     * user should specify the index of the task in the list
     * which he or she intends to mark as done.
     *
     * @param s User input containing the index of the task to be marked as done
     *          in the list of tasks, in String format.
     * @throws InvalidInputException If the user provided a non-integer index
     *                               in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is
     *                                  not in the list.
     */
    public String markDone(String s)
            throws InvalidInputException, ListOutOfBoundsException {
        int index;
        try {
            String[] sArray = s.split(" ");
            index = Integer.parseInt(sArray[1]) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException();
        }

        try {
            tasks.get(index).setDone();

            String output = "Nice! I've marked this task as done:\n"
                    + tasks.get(index).toString();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    /**
     * Deletes a task that is specified by the user from the
     * list of tasks. The user should specify the index of the
     * task in the list which he or she intends to delete.
     *
     * @param s User input containing the index of the task to be deleted from
     *          the list of tasks, in String format.
     * @throws InvalidInputException If the user provided a non-integer index
     *                               in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is
     *                                  not in the list.
     */
    public String deleteTask(String s) throws InvalidInputException, ListOutOfBoundsException {
        int index;
        try {
            String[] sArray = s.split(" ");
            index = Integer.parseInt(sArray[1]) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException();
        }

        try {
            Task t = tasks.remove(index);

            String output = "Noted. I've removed this task:\n"
                    + t.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    /**
     * Returns the list of tasks which the user currently has in his/her
     * task list.
     *
     * @return The list of tasks which the user currently has.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
