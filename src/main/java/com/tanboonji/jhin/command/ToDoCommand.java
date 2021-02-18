package com.tanboonji.jhin.command;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.Task;
import com.tanboonji.jhin.model.ToDo;

/**
 * The ToDoCommand class contains information to execute the "todo" command.
 */
public class ToDoCommand extends Command {

    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the todo command you entered is invalid.\n"
            + "Please enter a todo event command in the following format:\n"
            + "todo <description>";
    private static final String SUCCESS_MESSAGE_FORMAT = "Got it. I've added this todo task:\n"
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
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        String taskSingularPlural = (taskList.getSize() > 1) ? TASK_PLURAL : TASK_SINGULAR;
        return String.format(SUCCESS_MESSAGE_FORMAT, newTask, taskList.getSize(), taskSingularPlural);
    }

    /**
     * Returns new todo command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New todo command.
     * @throws JhinException If user input does not match todo command format.
     */
    public static ToDoCommand parseArguments(String arguments) throws JhinException {
        if (arguments.isEmpty()) {
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        return new ToDoCommand(arguments);
    }
}
