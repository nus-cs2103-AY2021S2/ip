package com.tjtanjin.ip;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * The Parser class parses user input before calling the appropriate command classes for execution.
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
    private final TaskHandler taskHandler;
    private final AddCommand addCommand = new AddCommand();
    private final ByeCommand byeCommand = new ByeCommand();
    private final ListCommand listCommand = new ListCommand();
    private final DoneCommand doneCommand = new DoneCommand();
    private final DeleteCommand deleteCommand = new DeleteCommand();
    private final HelpCommand helpCommand = new HelpCommand();
    private final FindCommand findCommand = new FindCommand();

    /**
     * Constructor for Parser class that initialises all valid commands.
     */
    public Parser(TaskHandler taskHandler) {
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

        this.taskHandler = taskHandler;
    }

    /**
     * Parses input from user to determine action to take.
     * @param input input provided by user
     */
    public void parseInput(String input) {

        //program exits on bye
        if (input.toUpperCase().equals(Cmd.BYE.toString())) {
            byeCommand.execute();

        //program shows entered tasks on list
        } else if (input.toUpperCase().equals(Cmd.LIST.toString())) {
            listCommand.execute(taskHandler);

        //program marks task as complete on done
        } else if (input.toUpperCase().startsWith(Cmd.DONE.toString())) {
            int index = parseIndex("done", input);
            if (index != -1) {
                doneCommand.execute(taskHandler, index);
            }

        //program removes task on delete
        } else if (input.toUpperCase().startsWith(Cmd.DELETE.toString())) {
            int index = parseIndex("delete", input);
            if (index != -1) {
                deleteCommand.execute(taskHandler, index);
            }

        //program list help commands
        } else if (input.toUpperCase().equals(Cmd.HELP.toString())) {
            helpCommand.execute(cmdInfo);

        //program adds task on todo, deadline or event
        } else if (input.toUpperCase().startsWith(Cmd.TODO.toString())
                || input.toUpperCase().startsWith(Cmd.DEADLINE.toString())
                        || input.toUpperCase().startsWith(Cmd.EVENT.toString())
        ) {
            String taskType = parseTaskType(input);
            String taskName = parseTaskName(input);
            if (taskName == null) {
                return;
            }
            LocalDate[] taskDates = parseTaskDates(input);
            if (!taskType.equalsIgnoreCase(Cmd.TODO.toString()) && taskDates[0] == null) {
                return;
            }
            addCommand.execute(taskHandler, taskType, taskName, taskDates);

        //program finds task by name on find
        } else if (input.toUpperCase().startsWith(Cmd.FIND.toString())) {
            String taskName = parseTaskName(input);
            if (taskName != null) {
                findCommand.execute(taskHandler, taskName);
            }

        //program informs user of invalid input
        } else {
            Ui.showError("Invalid instruction, type 'help' for more options.");
        }
    }

    /**
     * Parses index from user for done and delete command.
     * @param cmd cmd from user (done or delete)
     * @param input input provided by user
     */
    public int parseIndex(String cmd, String input) {
        String[] parsedString = input.split("\\s+");

        try {
            int index = Integer.parseInt(parsedString[1]) - 1;
            if (index < taskHandler.getTasks().size() && index >= 0) {
                return index;
            } else {
                throw new DukeException();
            }
        } catch (IndexOutOfBoundsException | DukeException e) {
            if (cmd.equals("done")) {
                Ui.showError("Usage for done: " + cmdInfo.get(Cmd.DONE.toString()));
            } else {
                Ui.showError("Usage for delete: " + cmdInfo.get(Cmd.DELETE.toString()));
            }
            return -1;
        } catch (NumberFormatException e) {
            if (cmd.equals("done")) {
                Ui.showError("Index of task to mark as done must be a number!");
            } else {
                Ui.showError("Index of task to delete must be a number!");
            }
            return -1;
        }
    }

    /**
     * Parses task type from user input
     * @param input input provided by user
     */
    public String parseTaskType(String input) {
        //split input on first space to retrieve task type
        String[] parsedString = input.split("\\s+", 2);
        return parsedString[0];
    }

    /**
     * Parses task name from user input
     * @param input input provided by user
     */
    public String parseTaskName(String input) {
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
                Ui.showError("Usage for todo: " + cmdInfo.get(Cmd.TODO.toString()));
            } else if (taskType.equalsIgnoreCase("DEADLINE")) {
                Ui.showError("Usage for deadline: " + cmdInfo.get(Cmd.DEADLINE.toString()));
            } else if (taskType.equalsIgnoreCase("EVENT")) {
                Ui.showError("Usage for event: " + cmdInfo.get(Cmd.EVENT.toString()));
            } else if (taskType.equalsIgnoreCase("FIND")) {
                Ui.showError("Usage for find: " + cmdInfo.get(Cmd.FIND.toString()));
            } else {
                Ui.showError("Invalid instruction, perhaps you meant todo, deadline or event?");
            }
            return null;
        }
    }

    /**
     * Parses task due date from user input
     * @param input input provided by user
     */
    public LocalDate[] parseTaskDates(String input) {
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
                Ui.showError("Invalid date specified, please adhere to the format: /by YYYY-MM-DD");
            } else {
                Ui.showError("Invalid date specified, "
                        + "please adhere to the format: /from YYYY-MM-DD /to YYYY-MM-DD");
            }
        }
        return taskDates;
    }
}
