package fakebot;

import java.time.LocalDate;
import java.time.LocalTime;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.command.CommandType;
import fakebot.task.Deadlines;
import fakebot.task.Events;
import fakebot.task.Task;
import fakebot.task.TaskList;
import fakebot.task.ToDos;


/**
 * Fakebot class, main class for Fakebot.
 */
public class FakeBot {
    private static final String OLD_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LOGO = " ______      _  ________   ____   ____ _______ \n"
            + "|  ____/ \\  | |/ /  ____| |  _ \\ / __ \\__   __|\n"
            + "| |__ /  \\  | ' /| |__    | |_) | |  | | | |   \n"
            + "|  __/ /\\ \\ |  < |  __|   |  _ <| |  | | | |\n"
            + "| | / ____ \\| . \\| |____  | |_) | |__| | | |\n"
            + "|_|/_/    \\_\\_|\\_\\______| |____/ \\____/  |_|\n";


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

    private static final String SAVE_FILE_PATH = "/data/";
    private static final String SAVE_FILE_NAME = "savedHistory.txt";

    private static Ui ui;

    private TaskList taskList;
    private Storage storage;

    /**
     * Class constructor specifying save file name and save file path.
     */
    public FakeBot() {
        this(SAVE_FILE_PATH, SAVE_FILE_NAME);
    }
    /**
     * Class constructor specifying save file name and save file path.
     */
    public FakeBot(String saveFileName, String saveFilePath) {
        storage = new Storage(SAVE_FILE_NAME, SAVE_FILE_PATH);
        taskList = new TaskList(storage.tryReadTaskFile());
    }

    /**
     * Saves History to Storage.
     */
    public void saveHistory() {
        storage.writeTasksToFIle(taskList);
    }

    /**
     * Returns Hello Message used at the start of the project.
     *
     * @return Returns Hello Message
     */
    public String getHelloMessage() {
        return Parser.getBotMPrintMessage("Hello from\n" + LOGO + "What can I do for you?");
    }

    /**
     * Returns message to show that the task is done.
     *
     * @param task Task to print.
     * @return Returns Done Message
     */
    public String getDoneMessage(Task task) {
        return Parser.getBotMPrintMessage("Nice! I've marked this task as done:\n " + task.toString());
    }


    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task Deleted Task.
     * @return Returns Delete Message
     */
    public String getDeleteMessage(Task task) {
        return Parser.getBotMPrintMessage("Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task Added Task.
     * @return Returns Added Task Message
     */
    public String getAddedTaskMessage(Task task) {
        return Parser.getBotMPrintMessage("Got it. I've added this task: \n  " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Process Command given by user.
     * Returns process command result message
     *
     * @param command Total number of Task Left.
     * @return Returns process command result message
     */
    public String processCommand(Command command) {
        switch (command.getCommand()) {
        case BYE:
            return "";
        case LIST:
            return Parser.getTaskListPrintMessage(taskList);
        case DONE:
            return processDoneCommand(command);
        case TODO:
            return processTodoCommand(command);
        case DEADLINE:
            return processDeadlineCommand(command);
        case EVENT:
            return processEventCommand(command);
        case DELETE:
            return processDeleteCommand(command);
        case FIND:
            return Parser.getTaskListPrintMessage(new TaskList(taskList.find(command.getDescription())));
        default:
            assert false : "All command type should be handle in switch";
            break;
        }

        return "";
    }

    /**
     * Process Done Command.
     *
     * @param command Command to Process.
     * @return Returns process Done command result message
     */
    private String processDoneCommand(Command command) {
        int doneIndex = Integer.parseInt(command.getDescription()) - 1;
        taskList.getTask(doneIndex).markComplete();
        saveHistory();
        return getDoneMessage(taskList.getTask(doneIndex));
    }

    /**
     * Process Todos Command.
     *
     * @param command Command to Process.
     * @return Returns process ToDos command result message
     */
    private String processTodoCommand(Command command) {
        ToDos todoTask = new ToDos(command.getDescription());
        taskList.addTask(todoTask);
        saveHistory();
        return getAddedTaskMessage(todoTask);
    }

    /**
     * Process Deadline Command.
     *
     * @param command Command to Process.
     * @return Returns process Deadline command result message
     */
    private String processDeadlineCommand(Command command) {
        String[] deadlineDetails = command.getDescription().split(DEADLINE_SPLIT_REGEX);
        String[] dates = deadlineDetails[1].split(" ");
        LocalDate date = LocalDate.parse(dates[0]);
        LocalTime time = LocalTime.parse(dates[1]);
        Deadlines deadlineTask = new Deadlines(deadlineDetails[0], date, time);
        taskList.addTask(deadlineTask);
        saveHistory();
        return getAddedTaskMessage(deadlineTask);
    }

    /**
     * Process Event Command.
     *
     * @param command Command to Process.
     * @return Returns process Event command result message
     */
    private String processEventCommand(Command command) {
        String[] eventDetails = command.getDescription().split(EVENT_SPLIT_REGEX);
        String[] eventDates = eventDetails[1].split(" ");
        LocalDate startDate = LocalDate.parse(eventDates[0]);
        LocalTime startTime = LocalTime.parse(eventDates[1]);
        LocalDate endDate = LocalDate.parse(eventDates[2]);
        LocalTime endTime = LocalTime.parse(eventDates[3]);
        Events eventTask = new Events(eventDetails[0], startDate, startTime, endDate, endTime);
        taskList.addTask(eventTask);
        saveHistory();
        return getAddedTaskMessage(eventTask);
    }

    /**
     * Process Delete Command.
     *
     * @param command Command to Process.
     */
    private String processDeleteCommand(Command command) {
        int deleteIndex = Integer.parseInt(command.getDescription()) - 1;
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        saveHistory();
        return getDeleteMessage(deletedTask);
    }

    /**
     * Validates User Input.
     *
     * @param command Command String that is yet to be parsed.
     */
    public Command validateCommand(String command) throws CommandException {
        if (command.equals(EXIT_COMMAND)) {
            return new Command(CommandType.BYE);
        } else if (command.equals(LIST_COMMAND)) {
            return new Command(CommandType.LIST);
        }

        if (command.equals(DONE_COMMAND) || command.equals(DELETE_COMMAND)) {
            throw new CommandException("☹ OOPS!!! You must indicate the index of the Tasks to be " + command + ".");
        }
        if (command.equals(TODO_COMMAND) || command.equals(DEADLINE_COMMAND)
                || command.equals(EVENT_COMMAND) || command.equals(FIND_COMMAND)) {
            throw new CommandException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }

        int firstSplit = command.indexOf(' ');
        if (firstSplit < 0) {
            throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String commandName = command.substring(0, firstSplit);
        String description = command.substring(firstSplit + 1);

        if (commandName.equals(DONE_COMMAND) || commandName.equals(DELETE_COMMAND) || commandName.equals(TODO_COMMAND)
                || commandName.equals(EVENT_COMMAND) || commandName.equals(DEADLINE_COMMAND)
                || commandName.equals(FIND_COMMAND)) {
            if (description.isEmpty()) {
                throw new CommandException("☹ OOPS!!! The description of a " + commandName + " cannot be empty.");
            } else if (commandName.equals(DONE_COMMAND) || commandName.equals(DELETE_COMMAND)) {
                try {
                    int index = Integer.parseInt(description);
                    if (index > taskList.getSize() || index < 1) {
                        throw new CommandException("☹ OOPS!!! Task number out of range.");
                    }
                } catch (NumberFormatException e) {
                    throw new CommandException("☹ OOPS!!! Invalid Task Index Format.");
                }
                if (commandName.equals(DONE_COMMAND)) {
                    return new Command(CommandType.DONE, description);

                } else if (commandName.equals(DELETE_COMMAND)) {
                    return new Command(CommandType.DELETE, description);
                }
            } else if (commandName.equals(FIND_COMMAND)) {
                return new Command(CommandType.FIND, description);
            } else if (commandName.equals(TODO_COMMAND)) {
                return new Command(CommandType.TODO, description);
            } else if (commandName.equals(DEADLINE_COMMAND)) {
                if (!description.contains(DEADLINE_SPLIT_REGEX)) {
                    throw new CommandException("☹ OOPS!!! The description of a "
                            + DEADLINE_COMMAND + " must contain Date indicated by \""
                            + DEADLINE_SPLIT_REGEX + "\".");
                }
                try {
                    String[] deadlineDetails = description.split(DEADLINE_SPLIT_REGEX);
                    String[] dates = deadlineDetails[1].split(" ");
                    LocalDate date = LocalDate.parse(dates[0]);
                    LocalTime time = LocalTime.parse(dates[1]);
                } catch (Exception e) {
                    throw new CommandException("☹ OOPS!!! The Date format of a "
                            + DEADLINE_COMMAND + " must be yyyy-mm-dd hh:ss.");

                }

                return new Command(CommandType.DEADLINE, description);
            } else if (commandName.equals(EVENT_COMMAND)) {
                if (!description.contains(EVENT_SPLIT_REGEX)) {
                    throw new CommandException("☹ OOPS!!! The description of a " + EVENT_COMMAND
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
                    throw new CommandException("☹ OOPS!!! The Date format of a "
                            + EVENT_COMMAND + " must be yyyy-mm-dd hh:ss yyyy-mm-dd hh:ss.");
                }
                return new Command(CommandType.EVENT, description);
            }
        }
        throw new CommandException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
