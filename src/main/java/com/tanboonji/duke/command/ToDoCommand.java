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
    private static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
            + "%s\n"
            + "Now you have %d %s.";
    private static final String TASK_SINGULAR = "task";
    private static final String TASK_PLURAL = "tasks";
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

        String taskSingularPlural = (taskList.getSize() > 1) ? TASK_PLURAL : TASK_SINGULAR;
        return String.format(SUCCESS_MESSAGE, newTask, taskList.getSize(), taskSingularPlural);
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
