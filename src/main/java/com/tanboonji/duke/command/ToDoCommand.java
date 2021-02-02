package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.Task;
import com.tanboonji.duke.model.ToDo;

public class ToDoCommand extends Command {

    public static final String COMMAND = "todo";
    private static final String ERROR_MESSAGE = "☹ Sorry, please enter a description for the todo.\n"
            + "\tCommand: todo [description]";

    private final String description;

    private ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute() throws DukeException {
        Task newTask = new ToDo(description);
        taskList.addTask(newTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n\t")
                .append(newTask)
                .append("\nNow you have ")
                .append(taskList.getSize());
        if (taskList.getSize() == 1) {
            builder.append(" task");
        } else {
            builder.append(" tasks");
        }
        return builder.toString();
    }

    @Override
    public boolean shouldSave() {
        return true;
    }

    public static ToDoCommand parseArguments(String input) throws DukeException {
        if (input.trim().equals("")) {
            throw new DukeException(ERROR_MESSAGE);
        }
        return new ToDoCommand(input);
    }
}
