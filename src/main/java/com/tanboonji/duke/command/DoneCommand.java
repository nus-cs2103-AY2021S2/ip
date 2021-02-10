package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Task;

/**
 * The DoneCommand class contains information to execute the "done" command.
 */
public class DoneCommand extends Command {

    /** String to execute this command */
    public static final String COMMAND = "done";
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a valid task number.\n"
            + "\tCommand: done [task number]";
    private static final String HEADER = "Nice! I've marked this task as done:\n\t";

    private final int taskIndex;

    private DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute() throws DukeException {
        try {
            Task task = taskList.markAsDone(taskIndex);
            return HEADER + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static DoneCommand parseArguments(String input) throws DukeException {
    /**
     * Returns new done command after parsing command argument.
     *
     * @param argument Command argument.
     * @return New done command.
     * @throws DukeException If user input is not an integer.
     */
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(input) - 1;
            return new DoneCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }
}
