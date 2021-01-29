package com.tjtanjin.steve.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.tjtanjin.steve.commands.ByeCommand;
import com.tjtanjin.steve.commands.CommandHandler;
import com.tjtanjin.steve.commands.DeadlineCommand;
import com.tjtanjin.steve.commands.DeleteCommand;
import com.tjtanjin.steve.commands.DoneCommand;
import com.tjtanjin.steve.commands.EventCommand;
import com.tjtanjin.steve.commands.FindCommand;
import com.tjtanjin.steve.commands.HelpCommand;
import com.tjtanjin.steve.commands.ListCommand;
import com.tjtanjin.steve.commands.TodoCommand;

/**
 * Parser class parses user input and returns a response. It also deals directly with the CommandHandler
 * for command execution.
 */
public class Parser {

    //tracks all valid commands
    private enum Cmd {
        BYE,
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        HELP,
        FIND
    }

    //all commands
    private final ByeCommand BYE_COMMAND;
    private final DeadlineCommand DEADLINE_COMMAND;
    private final DeleteCommand DELETE_COMMAND;
    private final DoneCommand DONE_COMMAND;
    private final EventCommand EVENT_COMMAND;
    private final FindCommand FIND_COMMAND;
    private final HelpCommand HELP_COMMAND;
    private final ListCommand LIST_COMMAND;
    private final TodoCommand TODO_COMMAND;

    /**
     * Constructor for Parser class that initialises all valid commands.
     */
    public Parser(CommandHandler commandHandler) {
        this.BYE_COMMAND = commandHandler.getByeCommand();
        this.DEADLINE_COMMAND = commandHandler.getDeadlineCommand();
        this.DELETE_COMMAND = commandHandler.getDeleteCommand();
        this.DONE_COMMAND = commandHandler.getDoneCommand();
        this.EVENT_COMMAND = commandHandler.getEventCommand();
        this.FIND_COMMAND = commandHandler.getFindCommand();
        this.HELP_COMMAND = commandHandler.getHelpCommand();
        this.LIST_COMMAND = commandHandler.getListCommand();
        this.TODO_COMMAND = commandHandler.getTodoCommand();
    }

    /**
     * Parses input from user to determine action to take.
     *
     * @param input input provided by user
     * @return string response after operation is done
     */
    public String parseInput(String input) {

        String[] parsedInput = input.split(" ", 2);

        String command = parsedInput[0].toUpperCase();
        if (!isValidCommand(command)) {
            return "Error: Invalid instruction, type 'help' for more options";
        }

        try {
            switch (Cmd.valueOf(command)) {
            case BYE:
                BYE_COMMAND.execute();
                return null;
            case DEADLINE:
                String deadlineTaskName = parseTaskName(input);
                LocalDate[] deadlineTaskDates = parseTaskDates(input);
                return DEADLINE_COMMAND.execute(command, deadlineTaskName, deadlineTaskDates);
            case DELETE:
                int deleteIndex = parseIndex("delete", input);
                return DELETE_COMMAND.execute(deleteIndex);
            case DONE:
                int doneIndex = parseIndex("done", input);
                return DONE_COMMAND.execute(doneIndex);
            case EVENT:
                String eventTaskName = parseTaskName(input);
                LocalDate[] eventTaskDates = parseTaskDates(input);
                return EVENT_COMMAND.execute(command, eventTaskName, eventTaskDates);
            case FIND:
                String searchTerm = parseTaskName(input);
                return FIND_COMMAND.execute(searchTerm);
            case HELP:
                return HELP_COMMAND.execute();
            case LIST:
                return LIST_COMMAND.execute();
            case TODO:
                String todoTaskName = parseTaskName(input);
                return TODO_COMMAND.execute(command, todoTaskName);
            default:
                return "Error: Invalid instruction, type 'help' to see the options.";
            }
        } catch (SteveInvalidFormatException | SteveInvalidParamsException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if a command provided by user is valid.
     *
     * @param command command provided by user
     * @return true if command is valid, false otherwise
     */
    private boolean isValidCommand(String command) {
        Cmd[] cmds = Cmd.values();
        for (Cmd cmd: cmds) {
            if (cmd.name().equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Parses index from user for done and delete command.
     *
     * @param cmd cmd from user (done or delete)
     * @param input input provided by user
     * @return index of task
     */
    public int parseIndex(String cmd, String input)
            throws SteveInvalidFormatException, SteveInvalidParamsException {
        String[] parsedString = input.split("\\s+");

        try {
            return Integer.parseInt(parsedString[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            if (cmd.equals("done")) {
                throw new SteveInvalidFormatException("Info: Usage for done: " + DONE_COMMAND.getDescription());
            } else {
                throw new SteveInvalidFormatException("Info: Usage for delete: " + DELETE_COMMAND.getDescription());
            }
        } catch (NumberFormatException e) {
            if (cmd.equals("done")) {
                throw new SteveInvalidParamsException("Info: Index of task to mark as done must be a number!");
            } else {
                throw new SteveInvalidParamsException("Info: Index of task to delete must be a number!");
            }
        }
    }

    /**
     * Parses task name from user input.
     *
     * @param input input provided by user
     * @return name of task
     */
    public String parseTaskName(String input) throws SteveInvalidFormatException {
        String[] parsedString = input.split("\\s+", 2);
        String taskType = parsedString[0];
        String taskDetails;
        String taskName;

        try {
            taskDetails = parsedString[1];
            if (taskType.toUpperCase().equals(Cmd.TODO.toString())) {
                taskName = taskDetails.trim();
            } else if (taskType.toUpperCase().equals(Cmd.DEADLINE.toString())) {
                taskName = taskDetails.split("/by", 2)[0].trim();
            } else {
                taskName = taskDetails.split("/from", 2)[0].trim();
            }
            return taskName;
        } catch (IndexOutOfBoundsException e) {
            if (taskType.equalsIgnoreCase("TODO")) {
                throw new SteveInvalidFormatException("Info: Usage for todo: "
                        + TODO_COMMAND.getDescription());
            } else if (taskType.equalsIgnoreCase("DEADLINE")) {
                throw new SteveInvalidFormatException("Info: Usage for deadline: "
                        + DEADLINE_COMMAND.getDescription());
            } else if (taskType.equalsIgnoreCase("EVENT")) {
                throw new SteveInvalidFormatException("Info: Usage for event: "
                        + EVENT_COMMAND.getDescription());
            } else {
                throw new SteveInvalidFormatException("Info: Usage for find: "
                        + FIND_COMMAND.getDescription());
            }
        }
    }

    /**
     * Parses task due date from user input.
     *
     * @param input input provided by user
     * @return array of dates for task
     */
    public LocalDate[] parseTaskDates(String input) throws SteveInvalidFormatException {
        String[] parsedString = input.split("\\s+", 2);
        String taskType = parsedString[0];
        String taskDetails = parsedString[1];
        LocalDate[] taskDates = new LocalDate[2];

        try {
            if (taskType.toUpperCase().equals(Cmd.DEADLINE.toString())) {
                taskDates[0] = LocalDate.parse(taskDetails.split("/by", 2)[1].trim());
            } else if (taskType.toUpperCase().equals(Cmd.EVENT.toString())) {
                String[] startEndDates = taskDetails.split("/from", 2)[1].split("/to", 2);
                taskDates[0] = LocalDate.parse(startEndDates[0].trim());
                taskDates[1] = LocalDate.parse(startEndDates[1].trim());
            }
        } catch (IndexOutOfBoundsException | NullPointerException | DateTimeException e) {
            if (taskType.equalsIgnoreCase(Cmd.DEADLINE.toString())) {
                throw new SteveInvalidFormatException("Error: Invalid date specified, "
                        + "please adhere to the format: /by YYYY-MM-DD");
            } else {
                throw new SteveInvalidFormatException("Error: Invalid date specified, "
                        + "please adhere to the format: /from YYYY-MM-DD /to YYYY-MM-DD");
            }
        }
        return taskDates;
    }
}
