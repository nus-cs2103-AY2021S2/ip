package fakebot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.command.CommandType;
import fakebot.task.Deadline;
import fakebot.task.Event;
import fakebot.task.Task;
import fakebot.task.TaskList;
import fakebot.task.Todo;

/**
 * Parser class use for processing syntax
 */
public class Parser {

    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String SPLIT_REGEX = "-'@,-@,1'-";

    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE_SPLIT_REGEX = " /by ";
    private static final String EVENT_COMMAND = "event";
    private static final String EVENT_SPLIT_REGEX = " /at ";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";


    /**
     * Enum for different task type.
     */
    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * Converts Task to String.
     *
     * @param stringList List of string to be converted to string.
     * @return Return string.
     */
    public static String convertStringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            builder.append(s);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Converts Task to String.
     *
     * @param task Task to be parsed to string.
     * @return Return parsed Task.
     */
    public static String convertTaskToString(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            stringBuilder.append(convertTodoToString(todo));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            stringBuilder.append(convertEventToString(event));
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            stringBuilder.append(convertDeadlineToString(deadline));

        }
        return stringBuilder.toString();
    }

    /**
     * Converts Todo to String.
     *
     * @param todo Todo to be parsed to string.
     * @return Return parsed Todo.
     */
    private static String convertTodoToString(Todo todo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TaskType.TODO.name());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(todo.isComplete());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(todo.getTaskName());
        return stringBuilder.toString();
    }

    /**
     * Converts Event to String.
     *
     * @param event Event to be parsed to string.
     * @return Return parsed Event.
     */
    private static String convertEventToString(Event event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TaskType.EVENT.name());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.isComplete());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.getTaskName());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.getStartDate());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.getStartTime());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.getEndDate());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(event.getEndTime());
        return stringBuilder.toString();
    }

    /**
     * Converts Deadline to String.
     *
     * @param deadline Deadline to be parsed to string.
     * @return Return parsed Deadline.
     */
    private static String convertDeadlineToString(Deadline deadline) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TaskType.DEADLINE.name());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(deadline.isComplete());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(deadline.getTaskName());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(deadline.getDeadlineDate());
        stringBuilder.append(SPLIT_REGEX);
        stringBuilder.append(deadline.getDeadlineTime());
        return stringBuilder.toString();
    }

    /**
     * Converts List of Task to List of String.
     *
     * @param taskList string of list.
     * @return Return list of String parsed from list of Task.
     */
    public static List<String> convertTasksToStrings(TaskList taskList) {
        List<String> strings = new ArrayList<String>();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task currentTask = taskList.getTask(i);
            strings.add(convertTaskToString(currentTask));
        }
        return strings;
    }

    /**
     * Converts String to Task.
     *
     * @param string Parsed Task.
     * @return Return task parsed from string.
     */
    public static Task convertStringToTask(String string) {
        String[] parts = string.split(SPLIT_REGEX);
        Task currentTask = null;
        switch (TaskType.valueOf(parts[0])) {
        case TODO:
            currentTask = new Todo(parts[2]);
            break;
        case EVENT:
            currentTask = new Event(parts[2], LocalDate.parse(parts[3]), LocalTime.parse(parts[4]),
                    LocalDate.parse(parts[5]), LocalTime.parse(parts[6]));
            break;
        case DEADLINE:
            currentTask = new Deadline(parts[2], LocalDate.parse(parts[3]), LocalTime.parse(parts[4]));
            break;
        default: break;
        }

        if (Boolean.parseBoolean(parts[1])) {
            currentTask.markComplete();
        }

        return currentTask;
    }

    /**
     * Converts List of String to List of Task.
     *
     * @param stringList string of list.
     * @return Return list of Task parsed from list of string.
     */
    public static List<Task> convertStringsToTasks(List<String> stringList) {
        List<Task> tasks = new ArrayList<Task>();

        for (String line : stringList) {
            tasks.add(convertStringToTask(line));
        }
        return tasks;
    }



    /**
     * Returns Standard Print Message.
     *
     * @param message Message to print.
     */
    public static String getBotMPrintMessage(String message) {
        String printMessage = DIVIDER + message + "\n" + DIVIDER;
        return printMessage;
    }

    /**
     * Returns List of String Print Message.
     *
     * @param startingMessage Starting message to print before the list.
     * @param messages        List of String to print.
     */
    public static String getStringListPrintMessage(String startingMessage, List<String> messages) {
        StringBuilder printMessage = new StringBuilder(DIVIDER);
        printMessage.append(startingMessage);
        for (int i = 1; i <= messages.size(); i++) {
            printMessage.append(i);
            printMessage.append(".");
            printMessage.append(messages.get(i - 1));
            printMessage.append("\n");
        }
        printMessage.append(DIVIDER);
        return printMessage.toString();
    }

    /**
     * Returns List of Task Print Message.
     *
     * @param taskList List of Task to convert.
     */
    public static String getTaskListPrintMessage(TaskList taskList) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            messages.add(taskList.getTask(i).toString());
        }
        return getStringListPrintMessage("Here are the tasks in your list:\n", messages);
    }


    /**
     * Parses User Input to Command.
     *
     * @param command Command String that is yet to be parsed.
     */
    public static Command parseUserInputToCommand(String command) throws CommandException {
        if(isCommandWithoutDescription(command)) {
            return parseCommandWithoutDescription(command);
        }

        int firstSplit = command.indexOf(' ');
        //Command with description should contain at least one space
        if (firstSplit < 0) {
            if(isCommandWithDescription(command)) {
                throw new CommandException("OOPS!!! The description of a " + command + " cannot be empty.");
            }else if (isCommandWithIndexDescription(command)) {
                throw new CommandException("OOPS!!! You must indicate the index of the Tasks to be " + command + ".");
            }
            throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String commandName = command.substring(0, firstSplit);
        String description = command.substring(firstSplit + 1);

        if(isCommandWithIndexDescription(commandName)) {
            return parseCommandWithIndexDescription(commandName, description);
        }
        if(isCommandWithDescription(commandName)) {
            return parseCommandWithDescription(commandName, description);
        }

        throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Check if the command need a description.
     * @param command Command String to be checked.
     * @return Returns true if the command do not need a description.
     */
    private static Boolean isCommandWithoutDescription(String command) {
        if (command.equals(EXIT_COMMAND)) {
            return true;
        } else if (command.equals(LIST_COMMAND)) {
            return true;
        }
        return false;
    }

    /**
     * parse the command that do not need description to Command class.
     * @param command Command String that do not need description.
     * @return Return Command.
     */
    private static Command parseCommandWithoutDescription(String command) {
        if (command.equals(EXIT_COMMAND)) {
            return new Command(CommandType.BYE);
        } else if (command.equals(LIST_COMMAND)) {
            return new Command(CommandType.LIST);
        }else {
            assert false : "Invalid calling of parse command without description";
            return null;
        }
    }

    /**
     * Check if the command need a index description.
     * @param command Command String to be checked.
     * @return Returns true if the command do not need a description.
     */
    private static Boolean isCommandWithIndexDescription(String command) {
        boolean isCommandWithIndexDescription = command.equals(DONE_COMMAND) || command.equals(DELETE_COMMAND);
        return isCommandWithIndexDescription;
    }

    /**
     * Parses the command that need a index description to Command class.
     * @param command Command String that need a index description.
     * @return Return Command that need a index description.
     */
    private static Command parseCommandWithIndexDescription(String command, String description) throws CommandException {
        if (description.isEmpty()) {
            throw new CommandException("OOPS!!! You must indicate the index of the Tasks to be " + command + ".");
        }

        try {
            int index = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new CommandException("OOPS!!! Invalid Task Index Format.");
        }

        if (command.equals(DONE_COMMAND)) {
            return new Command(CommandType.DONE, description);

        } else if (command.equals(DELETE_COMMAND)) {
            return new Command(CommandType.DELETE, description);
        }else {
            assert false : "Invalid calling of parse command with index description";
            return null;
        }
    }

    /**
     * Check if the command need a description.
     * @param command Command String to be checked.
     * @return Returns true if the command do need a description.
     */
    private static Boolean isCommandWithDescription(String command) {
        boolean isCommandWithDescription = command.equals(TODO_COMMAND) || command.equals(EVENT_COMMAND)
                || command.equals(DEADLINE_COMMAND) || command.equals(FIND_COMMAND);
        return isCommandWithDescription;
    }

    /**
     * Parses the command that need a description to Command class.
     * @param command Command name.
     * @param description Command description.
     * @return Return Command that need a description.
     */
    private static Command parseCommandWithDescription(String command, String description) throws CommandException {
        if (description.isEmpty()) {
            throw new CommandException("OOPS!!! The description of a " + command + " cannot be empty.");
        }
        if (command.equals(FIND_COMMAND)) {
            return parseFindCommand(description);
        } else if (command.equals(TODO_COMMAND)) {
            return parseTodoCommand(description);
        } else if (command.equals(DEADLINE_COMMAND)) {
            return parseDeadlineCommand(description);
        } else if (command.equals(EVENT_COMMAND)) {
            return parseEventCommand(description);
        }else {
            assert false : "Invalid calling of parse command with description";
            return null;
        }
    }


    /**
     * Parses todo command
     * @param description Command description.
     * @return Return Todo command.
     */
    private static Command parseTodoCommand(String description) {
        return new Command(CommandType.TODO, description);
    }

    /**
     * Parses find command
     * @param description Command description.
     * @return Return Find command.
     */
    private static Command parseFindCommand(String description) {
        return new Command(CommandType.FIND, description);
    }

    /**
     * Parses event command
     * @param description Command description.
     * @return Return Event command.
     */
    private static Command parseEventCommand(String description) throws CommandException {
        if (!description.contains(EVENT_SPLIT_REGEX)) {
            throw new CommandException("OOPS!!! The description of a " + EVENT_COMMAND
                    + " must contain Date and Duration indicated by \"" + EVENT_SPLIT_REGEX + "\".");
        }
        try {
            String[] eventDetails = description.split(EVENT_SPLIT_REGEX);
            String[] dates = eventDetails[1].split(" ");
            LocalDate startDate = LocalDate.parse(dates[0]);
            LocalTime startTime = LocalTime.parse(dates[1]);
            LocalDate endDate = LocalDate.parse(dates[2]);
            LocalTime endTime = LocalTime.parse(dates[3]);
        } catch (Exception e) {
            throw new CommandException("OOPS!!! The Date format of a "
                    + EVENT_COMMAND + " must be yyyy-mm-dd hh:ss yyyy-mm-dd hh:ss.");
        }
        return new Command(CommandType.EVENT, description);
    }

    /**
     * Parses deadline command.
     * @param description Command description.
     * @return Return Deadline command.
     */
    private static Command parseDeadlineCommand(String description) throws CommandException {
        if (!description.contains(DEADLINE_SPLIT_REGEX)) {
            throw new CommandException("OOPS!!! The description of a "
                    + DEADLINE_COMMAND + " must contain Date indicated by \""
                    + DEADLINE_SPLIT_REGEX + "\".");
        }
        try {
            String[] deadlineDetails = description.split(DEADLINE_SPLIT_REGEX);
            String[] dates = deadlineDetails[1].split(" ");
            LocalDate date = LocalDate.parse(dates[0]);
            LocalTime time = LocalTime.parse(dates[1]);
        } catch (Exception e) {
            throw new CommandException("OOPS!!! The Date format of a "
                    + DEADLINE_COMMAND + " must be yyyy-mm-dd hh:ss.");

        }
        return new Command(CommandType.DEADLINE, description);
    }
}
