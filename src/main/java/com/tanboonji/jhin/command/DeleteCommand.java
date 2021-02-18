package com.tanboonji.jhin.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Task;

/**
 * The DeleteCommand class contains information to execute the "delete" command.
 */
public class DeleteCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(\\d+)$");
    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the delete command you entered is invalid.\n"
                    + "Please enter a valid delete command in the following format:\n"
                    + "[delete, rm] <task number>";
    private static final String INVALID_TASK_NUMBER_MESSAGE_FORMAT =
            "Sorry, the task number '%s' you entered is invalid.\n"
                    + "Please enter a valid task number and try again.";
    private static final String SUCCESS_MESSAGE_FORMAT = "Noted! I've removed this task:\n"
            + "%s";

    private final int taskIndex;

    private DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() throws JhinException {
        try {
            Task task = taskList.deleteTask(taskIndex);
            return String.format(SUCCESS_MESSAGE_FORMAT, task);
        } catch (IndexOutOfBoundsException e) {
            int indexOneBase = taskIndex + 1;
            throw new InvalidCommandArgumentException(String.format(INVALID_TASK_NUMBER_MESSAGE_FORMAT, indexOneBase));
        }
    }

    /**
     * Returns new delete command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New delete command.
     * @throws JhinException If arguments does not match delete command format or task index is not an integer.
     */
    public static DeleteCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        try {
            int taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(String.format(INVALID_TASK_NUMBER_MESSAGE_FORMAT, arguments));
        }
    }
}
