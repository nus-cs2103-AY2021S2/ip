package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.dukeexceptions.EmptyArgumentException;
import duke.dukeexceptions.EmptyListException;
import duke.dukeexceptions.InvalidCommandException;
import duke.dukeexceptions.InvalidDateTimeException;
import duke.dukeexceptions.InvalidIndexInputException;
import duke.tasks.TaskList;

/**
 * Models a parser which parses input from the user into commands.
 */
public class Parser {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[d/M/yyyy HHmm][d MMM yy HHmm]"
            + "[dd-MM-yy HHmm]");
    private static final Pattern REGEX_CHECK_NUMBER = Pattern.compile("^[0-9]+$");
    private static final String INVALID_TASK_MSG = "Please input a valid task description!";
    private static final String MISSING_TASK_DATE = "Please input a valid task date in the following format: "
            + "'%s DESCRIPTION /%s DATE TIME'!";
    private static final String EMPTY_FIND_ARGUMENT = "Please pass a word after the 'find' command!";
    private static final String MISSING_INDEX_ARGUMENT = "Please pass an index after the '%s' command!";
    private static final String EXCEED_LIST_RANGE = "Please input an index from 1 to %d!";
    private static final String EMPTY_TASKLIST_DONE = "You have already done all tasks!";
    private static final String EMPTY_TASKLIST_DELETE = "There are no tasks to delete!";

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructors a Parser object, responsible for parsing input from the user.
     *
     * @param taskList the list of tasks.
     * @param storage the object in charge of writing to the local storage file.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns command associated with the command line input from user.
     *
     * @param input command line input from user.
     * @return command associated with input from user.
     * @throws EmptyArgumentException when only a 1 word command is passed without any following input.
     * @throws InvalidDateTimeException when date entered by user is not a valid date or not an acceptable date format.
     * @throws InvalidIndexInputException when index entered by user is not a number or not within range of 1 to
     *     the size of the TaskList.
     * @throws EmptyListException when trying to find by keyword but TaskList is empty.
     * @throws InvalidCommandException when no valid command is passed.
     */
    public Command parse(String input) throws EmptyArgumentException, InvalidDateTimeException,
            InvalidIndexInputException, EmptyListException, InvalidCommandException {
        String[] commandAndInput = input.split(" ", 2);
        String command = commandAndInput[0];

        switch (command) {
        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(commandAndInput);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(commandAndInput);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(commandAndInput);

        case FindCommand.COMMAND_WORD:
            return prepareFind(commandAndInput);

        case DoneCommand.COMMAND_WORD:
            return prepareDone(commandAndInput);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(commandAndInput);

        case ListCommand.COMMAND_WORD:
            return prepareList(commandAndInput);

        case ByeCommand.COMMAND_WORD:
            return prepareExit(commandAndInput);

        case HelpCommand.COMMAND_WORD:
            return prepareHelp(commandAndInput);

        default:
            throw new InvalidCommandException();
        }
    }

    private Command prepareToDo(String[] commandAndInput) throws EmptyArgumentException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(INVALID_TASK_MSG);
        }

        assert commandAndInput.length == 2;

        return new ToDoCommand(this.taskList, this.storage, commandAndInput[1]);
    }

    private Command prepareDeadline(String[] commandAndInput) throws EmptyArgumentException, InvalidDateTimeException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(INVALID_TASK_MSG);
        }

        assert commandAndInput.length == 2;

        String description = commandAndInput[1];
        String[] taskInputAndDate = description.split("/by ", 2);

        if (insufficientArgumentFromUser(taskInputAndDate)) {
            throw new EmptyArgumentException(String.format(MISSING_TASK_DATE, "deadline", "by"));
        }

        assert taskInputAndDate.length == 2;

        trimInputsInArray(taskInputAndDate);

        try {
            LocalDateTime dateTime = LocalDateTime.parse(taskInputAndDate[1], FORMATTER);
            return new DeadlineCommand(this.taskList, this.storage, taskInputAndDate[0], dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    private Command prepareEvent(String[] commandAndInput) throws EmptyArgumentException, InvalidDateTimeException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(INVALID_TASK_MSG);
        }

        assert commandAndInput.length == 2;

        String description = commandAndInput[1];
        String[] taskInputAndDate = description.split("/at ", 2);

        if (insufficientArgumentFromUser(taskInputAndDate)) {
            throw new EmptyArgumentException(String.format(MISSING_TASK_DATE, "event", "at"));
        }

        assert taskInputAndDate.length == 2;

        trimInputsInArray(taskInputAndDate);

        try {
            LocalDateTime dateTime = LocalDateTime.parse(taskInputAndDate[1], FORMATTER);
            return new EventCommand(this.taskList, this.storage, taskInputAndDate[0], dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    private Command prepareFind(String[] commandAndInput) throws EmptyArgumentException, EmptyListException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(EMPTY_FIND_ARGUMENT);
        }

        if (this.taskList.isEmpty()) {
            throw new EmptyListException();
        }

        assert commandAndInput.length == 2;

        return new FindCommand(this.taskList, this.storage, commandAndInput[1]);
    }

    private Command prepareDone(String[] commandAndInput) throws InvalidIndexInputException, EmptyArgumentException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(String.format(MISSING_INDEX_ARGUMENT, "done"));
        }

        assert commandAndInput.length == 2;

        int position = calcListPos(commandAndInput);

        if (this.taskList.isEmpty()) {
            throw new InvalidIndexInputException(EMPTY_TASKLIST_DONE);
        } else if (position >= this.taskList.getList().size() || position < 0) {
            throw new InvalidIndexInputException(String.format(EXCEED_LIST_RANGE, this.taskList.getList().size()));
        }

        return new DoneCommand(this.taskList, this.storage, position);
    }

    private Command prepareDelete(String[] commandAndInput) throws InvalidIndexInputException, EmptyArgumentException {
        if (insufficientArgumentFromUser(commandAndInput)) {
            throw new EmptyArgumentException(String.format(MISSING_INDEX_ARGUMENT, "delete"));
        }

        assert commandAndInput.length == 2;

        int position = calcListPos(commandAndInput);

        if (this.taskList.isEmpty()) {
            throw new InvalidIndexInputException(EMPTY_TASKLIST_DELETE);
        } else if (position >= this.taskList.getList().size() || position < 0) {
            throw new InvalidIndexInputException(String.format(EXCEED_LIST_RANGE, this.taskList.getList().size()));
        }

        return new DeleteCommand(this.taskList, this.storage, position);
    }

    private Command prepareList(String[] commandAndInput) throws InvalidCommandException {
        if (commandAndInput.length > 1) {
            throw new InvalidCommandException();
        }

        return new ListCommand(this.taskList, this.storage);
    }

    private Command prepareExit(String[] commandAndInput) throws InvalidCommandException {
        if (commandAndInput.length > 1) {
            throw new InvalidCommandException();
        }

        return new ByeCommand(this.taskList, this.storage);
    }

    private Command prepareHelp(String[] commandAndInput) {
        assert commandAndInput.length >= 1 && commandAndInput.length <= 2;

        if (commandAndInput.length == 1) {
            return new HelpCommand(this.taskList, this.storage);
        }

        return new HelpCommand(this.taskList, this.storage, commandAndInput[1]);
    }

    private void trimInputsInArray(String[] taskInputAndDate) {
        taskInputAndDate[0] = taskInputAndDate[0].trim();
        taskInputAndDate[1] = taskInputAndDate[1].trim();
    }

    private int calcListPos(String[] commandAndInput) throws InvalidIndexInputException {
        String taskIndex = commandAndInput[1];
        String command = commandAndInput[0];

        Matcher matcher = REGEX_CHECK_NUMBER.matcher(taskIndex);
        if (!matcher.find()) {
            throw new InvalidIndexInputException("'" + command + "' is command word; please pass a numerical index or "
                    + "start your task with another word!");
        }

        return Integer.parseInt(taskIndex) - 1;
    }

    private boolean insufficientArgumentFromUser(String[] commandAndInput) {
        return commandAndInput.length == 1 || (commandAndInput.length == 2 && commandAndInput[1].equals(""));
    }
}
