package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

import duke.exceptions.ParseException;
import duke.expenses.Expense;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

public class Parser {
    private TaskList taskList;
    /** ArrayList of Tasks obtained from TaskList **/
    private ArrayList<Task> tasks;

    /** A collection of commands Tasker understands **/
    public enum Command {
        LIST,
        LIST_E,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        SPEND,
        BYE
    }

    /**
     * Constructs a Parser object.
     * @param taskList TaskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.tasks = this.taskList.getTasks();
    }

    /**
     * Parses the lowercase input command into a uppercase one.
     * @param input lowercase command input
     * @return uppercase Command
     */
    public static Command parseCommand(String input) {
        Command c = Command.valueOf(input.toUpperCase(Locale.ROOT));
        return c;
    }

    /**
     * Credit to QY-H00 - who inspired me to implement a parser for specific commands.
     *
     * Returns a specific type of Task object by parsing the description after the given command.
     *
     * @param command type of task (eg. TODO, DEADLINE or EVENT)
     * @param description description of task
     * @return a task object corresponding to the input
     * @throws ParseException if parsing fails
     */
    public static Task parseDescription(Command command, String description) throws ParseException {
        switch (command) {
        case TODO:
            return parseToDo(description);
        case DEADLINE:
            return parseDeadline(description);
        case EVENT:
            return parseEvent(description);
        default:
            throw new ParseException("Invalid command!");
        }
    }

    /**
     * Returns an Expense object by parsing the description after the given command.
     *
     * @param command type of expense
     * @param description description of expense
     * @return an expense object corresponding to the input
     * @throws ParseException if parsing fails
     */
    public static Expense parseExpenseDescription(Command command, String description) throws ParseException {
        switch (command) {
        case SPEND:
            return parseSpend(description);
        default:
            throw new ParseException("Invalid command!");
        }
    }

    private static Expense parseSpend(String input) {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of a Spend cannot be empty.\n");
        }
        if (input.contains("/amt ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int indexOfEndOfDescription = input.indexOf("/amt ");
            String description = input.substring(0, indexOfEndOfDescription);
            int indexOfDate = input.indexOf("/date ");
            String amtStr = input.substring(indexOfEndOfDescription + 5, indexOfDate - 1);
            double amt = Double.parseDouble(amtStr);
            String dateStr = input.substring(indexOfDate + 6);
            LocalDate date = LocalDate.parse(dateStr);
            if (amt <= 0) {
                throw new ParseException("Input invalid! Please enter a proper spending amount... (how"
                        + " could you spend less than or equal to zero dollars??)");
            }
            return new Expense(description, amt, date);
        } else {
            throw new ParseException("Invalid input! Please enter using format: 'spend'_[description]_"
                    + "'/amt [amount spent]'_'/date YYYY-MM-DD'\n");
        }
    }


    /**
     * Returns a ToDoTask by parsing the input behind the command.
     * Used when the command is 'todo_[description]'.
     * This function works for non-empty inputs.
     *
     * @param input user input
     * @return a ToDoTask
     * @throws ParseException if description is empty
     */
    private static ToDoTask parseToDo(String input) throws ParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of a ToDoTask cannot be empty.\n");
        }
        if (input.charAt(0) == ' ') {
            input = input.substring(1);
        }
        return new ToDoTask(input, false);
    }

    /**
     * Returns a DeadlineTask by parsing the input behind the command.
     * Used when the command is 'deadline_[description]_/by_[YYYY-MM-DD]_/time_[HH:mm:ss]'.
     *
     * @param input input of users.
     * @return a task of Deadline corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static DeadlineTask parseDeadline(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of a DeadlineTask cannot be empty.\n");
        }
        if (input.contains("/by ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int indexOfEndOfDescription = input.indexOf("/by ");
            String description = input.substring(0, indexOfEndOfDescription);
            int indexOfTime = input.indexOf("/time ");
            String deadline = input.substring(indexOfEndOfDescription + 4, indexOfTime - 1);
            LocalDate date = LocalDate.parse(deadline);
            String deadTime = input.substring(indexOfTime + 6);
            LocalTime localTime = LocalTime.parse(deadTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

            return new DeadlineTask(description, false, date, localTime);
        } else {
            throw new ParseException("Invalid input! Please enter using format: 'deadline'_[description]_"
                    + "'/by YYYY-MM-DD'_'/time HH:mm:ss'\n");
        }
    }

    /**
     * Returns an Event by parsing the input behind the command.
     * Used when the command is event.
     * Works when the input is "{description} /at YYYY-MM-DD".
     *
     * @param input input of users.
     * @return a task of Event corresponding to the input.
     * @throws ParseException if the description is empty.
     * @throws DateTimeParseException if the format of data time is not correct.
     */
    private static EventTask parseEvent(String input) throws ParseException, DateTimeParseException {
        if (input.isEmpty() || input.equals(" ")) {
            throw new ParseException("Invalid input! Description of an EventTask cannot be empty.\n");
        }
        if (input.contains("/at ")) {
            if (input.charAt(0) == ' ') {
                input = input.substring(1);
            }
            int indexOfEndOfDescription = input.indexOf("/at ");
            String description = input.substring(0, indexOfEndOfDescription);
            int indexOfTime = input.indexOf("/time ");
            String deadline = input.substring(indexOfEndOfDescription + 4, indexOfTime - 1);
            LocalDate date = LocalDate.parse(deadline);
            String deadTime = input.substring(indexOfTime + 6);
            LocalTime localTime = LocalTime.parse(deadTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
            return new EventTask(description, false, date, localTime);
        } else {
            throw new ParseException("Invalid input! Please enter using format: 'event'_[description]_"
                    + "'/by YYYY-MM-DD'_'/time HH:mm:ss'\n");
        }
    }
}
