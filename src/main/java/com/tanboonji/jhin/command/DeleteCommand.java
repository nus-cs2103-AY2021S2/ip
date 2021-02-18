package com.tanboonji.jhin.command;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Task;

/**
 * The DeleteCommand class contains information to execute the "delete" command.
 */
public class DeleteCommand extends Command {

    private static final String ERROR_MESSAGE = "Sorry, please enter a valid task number.\n"
            + "Command: delete [task number]";
    private static final String SUCCESS_MESSAGE = "Noted! I've removed this task:\n"
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
            return String.format(SUCCESS_MESSAGE, task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns new delete command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New delete command.
     * @throws JhinException If user input is not an integer.
     */
    public static DeleteCommand parseArguments(String arguments) throws JhinException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
    }
}
