package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Task;

/**
 * The DeleteCommand class contains information to execute the "delete" command.
 */
public class DeleteCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "delete";
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid task number.\n"
            + "\tCommand: delete [task number]";
    private static final String HEADER = "Noted! I've removed this task:\n\t";

    private final int taskIndex;

    private DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() throws DukeException {
        try {
            Task task = taskList.deleteTask(taskIndex);
            return HEADER + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Returns new delete command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New delete command.
     * @throws DukeException If user input is not an integer.
     */
    public static DeleteCommand parseArguments(String arguments) throws DukeException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
