package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.exception.InvalidCommandArgumentException;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.model.ToDo;

/**
 * The ToDoCommand class contains information to execute the "todo" command.
 */
public class ToDoCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "todo";
    private static final String ERROR_MESSAGE = "Sorry, please enter a description for the todo.\n"
            + "\tCommand: todo [description]";
    private final String description;

    private ToDoCommand(String description) {
        this.description = description;
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
    public String execute() {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t")
                .append(newTask)
                .append("\nNow you have ")
                .append(taskList.getSize())
                .append(" task");
        if (taskList.getSize() > 1) {
            builder.append("s");
        }
        return builder.toString();
    }

    /**
     * Returns new todo command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New todo command.
     * @throws DukeException If user input does not match todo command format.
     */
    public static ToDoCommand parseArguments(String arguments) throws DukeException {
        if (arguments.trim().equals("")) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }
        return new ToDoCommand(arguments);
    }
}
