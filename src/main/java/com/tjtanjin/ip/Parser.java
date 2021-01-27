package com.tjtanjin.ip;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The Parser class parses user input and deals directly with the CommandHandler for command execution.
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

    //list storing commands/descriptions, ideally store in json file
    private final HashMap<String, String> cmdInfo = new HashMap<>();
    private final AddCommand addCommand;
    private final ByeCommand byeCommand;
    private final DeleteCommand deleteCommand;
    private final DoneCommand doneCommand;
    private final FindCommand findCommand;
    private final HelpCommand helpCommand;
    private final ListCommand listCommand;

    /**
     * Constructor for Parser class that initialises all valid commands.
     */
    public Parser(CommandHandler commandHandler) {
        this.addCommand = commandHandler.getAddCommand();
        this.byeCommand = commandHandler.getByeCommand();
        this.deleteCommand = commandHandler.getDeleteCommand();
        this.doneCommand = commandHandler.getDoneCommand();
        this.findCommand = commandHandler.getFindCommand();
        this.helpCommand = commandHandler.getHelpCommand();
        this.listCommand = commandHandler.getListCommand();
        cmdInfo.put(Cmd.BYE.toString(), "bye | Description: exits the program");
        cmdInfo.put(Cmd.LIST.toString(), "list | Description: list all entered tasks");
        cmdInfo.put(Cmd.DONE.toString(),
                "done <task index> | Description: marks by index a given task as done");
        cmdInfo.put(Cmd.TODO.toString(), "todo <name> | Description: adds a new todo task");
        cmdInfo.put(Cmd.DEADLINE.toString(),
                "deadline <name> /by <end date> | Description: adds a new deadline task");
        cmdInfo.put(Cmd.EVENT.toString(),
                "event <name> /from <start date> /to <end date> | Description: adds a new event task");
        cmdInfo.put(Cmd.DELETE.toString(), "delete <task index> | Description: delete by index a given task");
        cmdInfo.put(Cmd.HELP.toString(), "help | Description: list this help menu");
        cmdInfo.put(Cmd.FIND.toString(), "find <name> | Description: finds task by name");
    }

    /**
     * Parses input from user to determine action to take.
     * @param input input provided by user
     */
    public String parseInput(String input) {

        String[] parsedInput = input.split(" ", 2);

        String command = parsedInput[0].toUpperCase();
        if (!isValidCommand(command)) {
            return "Error: Invalid instruction, type /help for more options";
        }

        try {
            switch (Cmd.valueOf(command)) {
            case BYE:
                UiHandler.terminate("Good bye, see you soon! :D");
                byeCommand.execute();
                return null;
            case DEADLINE:
            case EVENT:
            case TODO:
                String taskName = parseTaskName(input);
                LocalDate[] taskDates = parseTaskDates(input);
                return addCommand.execute(command, taskName, taskDates);
            case DELETE:
                int deleteIndex = parseIndex("delete", input);
                return deleteCommand.execute(deleteIndex);
            case DONE:
                int doneIndex = parseIndex("done", input);
                return doneCommand.execute(doneIndex);
            case FIND:
                String searchTerm = parseTaskName(input);
                return findCommand.execute(searchTerm);
            case HELP:
                return helpCommand.execute(cmdInfo);
            case LIST:
                return listCommand.execute();
            default:
                return "Error: Invalid instruction, type 'help' to see the options.";
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

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
     * @param cmd cmd from user (done or delete)
     * @param input input provided by user
     */
    public int parseIndex(String cmd, String input) throws DukeException {
        String[] parsedString = input.split("\\s+");

        try {
            return Integer.parseInt(parsedString[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            if (cmd.equals("done")) {
                throw new DukeException("Info: Usage for done: " + cmdInfo.get(Cmd.DONE.toString()));
            } else {
                throw new DukeException("Info: Usage for delete: " + cmdInfo.get(Cmd.DELETE.toString()));
            }
        } catch (NumberFormatException e) {
            if (cmd.equals("done")) {
                throw new DukeException("Info: Index of task to mark as done must be a number!");
            } else {
                throw new DukeException("Info: Index of task to delete must be a number!");
            }
        }
    }

    /**
     * Parses task name from user input
     * @param input input provided by user
     */
    public String parseTaskName(String input) throws DukeException {
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
                throw new DukeException("Info: Usage for todo: " + cmdInfo.get(Cmd.TODO.toString()));
            } else if (taskType.equalsIgnoreCase("DEADLINE")) {
                throw new DukeException("Info: Usage for deadline: " + cmdInfo.get(Cmd.DEADLINE.toString()));
            } else if (taskType.equalsIgnoreCase("EVENT")) {
                throw new DukeException("Info: Usage for event: " + cmdInfo.get(Cmd.EVENT.toString()));
            } else if (taskType.equalsIgnoreCase("FIND")) {
                throw new DukeException("Info: Usage for find: " + cmdInfo.get(Cmd.FIND.toString()));
            } else {
                throw new DukeException("Info: Invalid instruction, perhaps you meant todo, deadline or event?");
            }
        }
    }

    /**
     * Parses task due date from user input
     * @param input input provided by user
     */
    public LocalDate[] parseTaskDates(String input) throws DukeException {
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
                throw new DukeException("Error: Invalid date specified, please adhere to the format: /by YYYY-MM-DD");
            } else {
                throw new DukeException("Error: Invalid date specified, "
                        + "please adhere to the format: /from YYYY-MM-DD /to YYYY-MM-DD");
            }
        }
        return taskDates;
    }
}
