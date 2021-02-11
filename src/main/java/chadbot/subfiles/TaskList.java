package chadbot.subfiles;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import chadbot.exceptions.DateFormatException;
import chadbot.exceptions.EmptyDateException;
import chadbot.exceptions.EmptyDescriptionException;
import chadbot.exceptions.InvalidInputException;
import chadbot.exceptions.ListOutOfBoundsException;
import chadbot.exceptions.LoadFailureException;
import chadbot.exceptions.TaskTypeErrorException;
import chadbot.task.Deadline;
import chadbot.task.Event;
import chadbot.task.Task;
import chadbot.task.TaskComparator;
import chadbot.task.ToDo;

/**
 * The TaskList class contains a list of tasks created by user input,
 * and allows the user to add, print, or delete tasks, as well as to mark a task in the list as done.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
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
     * Returns the task description specified in the user input.
     *
     * @param input The user input.
     * @param startIndex Start index of the description in the user input.
     * @return The task description specified in the user input.
     */
    private String getTaskDescription(String input, int startIndex) {
        int splitLimit = 2;
        String splitRegex = " /";
        String[] tempArray = input.split(splitRegex, splitLimit);
        return tempArray[0].substring(startIndex);
    }

    /**
     * Returns the date specified in the user input.
     *
     * @param input The user input.
     * @return The date specified in the user input.
     * @throws DateTimeParseException If the specified date is incorrectly formatted.
     */
    private LocalDate getTaskDate(String input) throws DateTimeParseException {
        int splitLimit = 2;
        int startIndex = 3;
        String splitRegex = " /";
        String[] tempArray = input.split(splitRegex, splitLimit);
        return LocalDate.parse(tempArray[1].substring(startIndex));
    }

    /**
     * Adds a to-do to the list of tasks.
     *
     * @param input User input triggering the addition of a to-do to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided for the to-do.
     */
    private void addTodo(String input) throws EmptyDescriptionException {
        String taskType = "todo";

        try {
            int startOfDescription = 5;
            String description = getTaskDescription(input, startOfDescription);
            tasks.add(new ToDo(description));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(taskType);
        }
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param input User input triggering the addition of a deadline to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided for the deadline.
     * @throws EmptyDateException If no date is specified for the deadline.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    private void addDeadline(String input) throws EmptyDescriptionException, EmptyDateException,
            DateFormatException {
        String taskType = "deadline";

        try {
            int startOfDescription = 9;
            String description = getTaskDescription(input, startOfDescription);
            LocalDate date = getTaskDate(input);
            tasks.add(new Deadline(description, date));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(taskType);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDateException(taskType);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param input User input triggering the addition of an event to the list of tasks.
     * @throws EmptyDescriptionException If no description is provided for the event.
     * @throws EmptyDateException If no date is specified for the event.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    private void addEvent(String input) throws EmptyDescriptionException, EmptyDateException,
            DateFormatException {
        String taskType = "event";

        try {
            int startOfDescription = 6;
            String description = getTaskDescription(input, startOfDescription);
            LocalDate date = getTaskDate(input);
            tasks.add(new Event(description, date));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(taskType);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDateException(taskType);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Adds a task to the list of tasks. Calls either addTodo,  addDeadline, or addEvent,
     * depending on the type of task specified by the user.
     *
     * @param input User input triggering the addition of a task to the list of tasks.
     * @return Duke's response to the user.
     * @throws EmptyDescriptionException If no description is provided for the task.
     * @throws EmptyDateException If no date or time is specified for the task,
     *                            which is either a deadline or an event.
     * @throws InvalidInputException If the task is neither a to-do, a deadline, nor an event.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    public String addTask(String input) throws EmptyDescriptionException, EmptyDateException,
            InvalidInputException, DateFormatException {
        int splitLimit = 2;
        String splitRegex = " ";

        String command = input.split(splitRegex, splitLimit)[0];

        switch (command) {
        case "todo":
            addTodo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
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
     * Adds a to-do, deadline, or event, to the list of tasks, based on previously saved data.
     *
     * @param data A line from the user's save data.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    public void addTaskFromData(String data) throws DateFormatException, LoadFailureException {
        String splitRegex = " \\| ";

        String[] sArray = data.split(splitRegex);

        try {
            switch (sArray[0]) {
            case "T":
                tasks.add(new ToDo(sArray[2]));
                break;
            case "D":
                tasks.add(new Deadline(sArray[2], LocalDate.parse(sArray[3])));
                break;
            case "E":
                tasks.add(new Event(sArray[2], LocalDate.parse(sArray[3])));
                break;
            default:
                throw new LoadFailureException();
            }

            String doneIndicator = "1";
            if (sArray[1].equals(doneIndicator)) {
                tasks.get(tasks.size() - 1).setDone();
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Prints the list of tasks added by the user till this point, based on the order they were added by the user.
     *
     * @return Duke's response to the user.
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
     * Prints the list of deadlines added by the user till this point, due on the date specified by the user,
     * based on the order they were added by the user.
     *
     * @param date The date specified by the user.
     * @return Duke's response to the user.
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
     * Prints the list of events added by the user till this point, happening on the date specified by the user,
     * based on the order they were added by the user.
     *
     * @param date The date specified by the user.
     * @return Duke's response to the user.
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
            output = new StringBuilder("You have no events occurring on " + date.toString() + ".");
        } else {
            int i = 1;
            output = new StringBuilder("Here are the events occurring on " + date.toString() + ":");
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
     * due or happening on the date specified by the user, based on the order they were added by the user.
     *
     * @param input The date specified by the user.
     * @return Duke's response to the user.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    public String printTasksOnDate(String input) throws DateFormatException {
        try {
            LocalDate date = LocalDate.parse(input);
            String upperOutput = printDeadlinesOnDate(date);
            String lowerOutput = printEventsOnDate(date);
            return upperOutput + "\n\n" + lowerOutput;
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Prints the list of tasks added by the user till this point, containing the keyword specified by the user,
     * ordered based on the current ordering of the list.
     *
     * @param input User input containing the keyword.
     * @return Duke's response to the user.
     */
    public String findTasksWithKeyword(String input) {
        int splitLimit = 2;
        String splitRegex = " ";
        StringBuilder output;
        ArrayList<Task> matchingTasks = new ArrayList<>();

        String keyword = input.split(splitRegex, splitLimit)[1].toLowerCase();

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
     * Returns the index specified by the user after parsing it as an int.
     *
     * @param input User input.
     * @return Index specified by the user, parsed as an int.
     * @throws InvalidInputException If the user provided a non-integer index in the user input.
     */
    private int getIndex(String input) throws InvalidInputException {
        try {
            int offset = 1;
            String splitRegex = " ";
            String[] sArray = input.split(splitRegex);
            return Integer.parseInt(sArray[1]) - offset;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Marks a task that is specified by the user as done. The user should specify the index of the task
     * in the list which he or she intends to mark as done.
     *
     * @param input User input containing the index of the task to be marked as done in the list of tasks,
     *              in String format.
     * @return Duke's response to the user.
     * @throws InvalidInputException If the user provided a non-integer index in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is not in the list.
     */
    public String markDone(String input) throws InvalidInputException, ListOutOfBoundsException {
        int index = getIndex(input);

        try {
            tasks.get(index).setDone();

            String output = "Nice! I've marked this task as done:\n" + tasks.get(index).toString();
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    /**
     * Deletes a task that is specified by the user from the list of tasks. The user should specify the index
     * of the task in the list which he or she intends to delete.
     *
     * @param input User input containing the index of the task to be deleted from the list of tasks,
     *              in String format.
     * @return Duke's response to the user.
     * @throws InvalidInputException If the user provided a non-integer index in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is not in the list.
     */
    public String deleteTask(String input) throws InvalidInputException, ListOutOfBoundsException {
        int index = getIndex(input);

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
     * Modifies the date of a deadline or event.
     *
     * @param task The task which the user is trying to modify.
     * @param date The new date of the deadline or event.
     * @throws TaskTypeErrorException If the user attempts to modify the date of a non-deadline and non-event.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    private void editTaskDate(Task task, String date) throws TaskTypeErrorException, DateFormatException {
        try {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                d.setDate(LocalDate.parse(date));
            } else if (task instanceof Event) {
                Event e = (Event) task;
                e.setDate(LocalDate.parse(date));
            } else {
                throw new TaskTypeErrorException();
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Modifies the description of a task.
     *
     * @param task The task which the user is trying to modify.
     * @param description The new description of the task.
     */
    private void editTaskDescription(Task task, String description) {
        task.setName(description);
    }

    /**
     * Edit a task that is specified by the user from the list of tasks. The user should specify the index
     * of the task in the list which he or she intends to edit, and the new description or date.
     *
     * @param input User input containing the index of the task to be edited, in String format.
     * @return Duke's response to the user.
     * @throws InvalidInputException If the user provided a non-integer index in the user input.
     * @throws ListOutOfBoundsException If the user provided an index which is not in the list.
     * @throws TaskTypeErrorException If the user attempts to modify the date of a non-deadline and non-event.
     * @throws DateFormatException If the specified date is incorrectly formatted.
     */
    public String editTask(String input) throws InvalidInputException, ListOutOfBoundsException,
            TaskTypeErrorException, DateFormatException {
        int index = getIndex(input);
        int splitLimit = 4;
        String splitRegex = " ";
        String[] sArray = input.split(splitRegex, splitLimit);

        try {
            int commandType = 2;
            int leftover = 3;
            String typeDate = "/date";
            String typeDescription = "/desc";
            Task t = tasks.get(index);
            String originalOutput = t.toString();

            if (sArray.length < 4) {
                throw new InvalidInputException();
            }

            if (sArray[commandType].equals(typeDate)) {
                editTaskDate(t, sArray[leftover]);
            } else if (sArray[commandType].equals(typeDescription)) {
                editTaskDescription(t, sArray[leftover]);
            } else {
                throw new InvalidInputException();
            }

            String output = "Noted. I've edited this task:\n"
                    + originalOutput + "\n \uD83E\uDC13 \n"
                    + t.toString() + "\n";
            System.out.println(output);
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    /**
     * Returns the list of tasks which the user currently has in his/her task list.
     *
     * @return The list of tasks which the user currently has.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Function which helps to separate the list of tasks into three separate lists, based on their type.
     *
     * @param todos The list which will be containing the to-dos.
     * @param deadlines The list which will be containing the deadlines.
     * @param events The list which will be containing the events.
     */
    private void separateTasksByType(ArrayList<ToDo> todos, ArrayList<Deadline> deadlines, ArrayList<Event> events) {
        while (!tasks.isEmpty()) {
            Task t = tasks.remove(0);
            if (t instanceof ToDo) {
                todos.add((ToDo) t);
            } else if (t instanceof Deadline) {
                deadlines.add((Deadline) t);
            } else if (t instanceof Event) {
                events.add((Event) t);
            }
        }
    }

    /**
     * Sorts the list of tasks first based on their type, followed by their date.
     */
    public void sortTasksByTypeAndDate() {
        ArrayList<ToDo> todos = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        separateTasksByType(todos, deadlines, events);

        Collections.sort(deadlines);
        Collections.sort(events);

        tasks.addAll(todos);
        tasks.addAll(deadlines);
        tasks.addAll(events);
    }

    /**
     * Sorts the list of tasks based on their type.
     */
    public void sortTasksByType() {
        ArrayList<ToDo> todos = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        separateTasksByType(todos, deadlines, events);

        tasks.addAll(todos);
        tasks.addAll(deadlines);
        tasks.addAll(events);
    }

    /**
     * Sorts the list of tasks based on their name, in alphabetical ordering.
     */
    public void sortTasksByName() {
        tasks.sort(new TaskComparator());
    }

    /**
     * Displays the number of to-dos, deadlines, and events that the user currently has.
     *
     * @return The number of to-dos, deadlines, and events that the user currently has.
     */
    public String displayStats() {
        int numTodos = 0;
        int numDeadlines = 0;
        int numEvents = 0;

        for (Task t : tasks) {
            if (t instanceof ToDo) {
                numTodos++;
            } else if (t instanceof Deadline) {
                numDeadlines++;
            } else if (t instanceof Event) {
                numEvents++;
            }
        }

        String output = "Your task list currently consists of:\n"
                + numTodos + " to-dos,\n"
                + numDeadlines + " deadlines, and\n"
                + numEvents + " events.";
        System.out.println(output);
        return output;
    }

}
