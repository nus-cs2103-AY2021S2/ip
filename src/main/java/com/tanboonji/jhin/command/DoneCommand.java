package com.tanboonji.jhin.command;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Task;

/**
 * The DoneCommand class contains information to execute the "done" command.
 */
public class DoneCommand extends Command {

    private static final String ERROR_MESSAGE = "Sorry, please enter a valid task number.\n"
            + "Command: done [task number]";
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n"
            + "%s";

    private final int taskIndex;

    private DoneCommand(int taskIndex) {
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
            Task task = taskList.markAsDone(taskIndex);
            return String.format(SUCCESS_MESSAGE, task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns new done command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New done command.
     * @throws JhinException If user input is not an integer.
     */
    public static DoneCommand parseArguments(String arguments) throws JhinException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments) - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
    }
}
