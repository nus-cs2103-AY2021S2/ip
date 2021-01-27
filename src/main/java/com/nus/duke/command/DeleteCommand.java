package com.nus.duke.command;

import com.nus.duke.data.Task;
import com.nus.duke.ui.TextUi;

public class DeleteCommand extends Command {

    public static final String COMMAND = "delete";
    public static final String IMPROPER_USAGE_FORMAT = "Improper command format";
    public static final String USAGE_MESSAGE = COMMAND + ": Delete a task\n"
            + "Usage: delete [index]\n"
            + "Example: delete 3";
    public static final String SUCCESS_MESSAGE_TEMPLATE = "Noted. I have removed this task:\n%s"
            + "\nYou now have %d tasks in the list.";
    public static final String INDEX_ERROR_MESSAGE_TEMPLATE = "Invalid index number %d";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            final Task deletedTask = this.taskList.removeTask(this.index);
            final int taskSize = this.taskList.getSize();
            return String.format(SUCCESS_MESSAGE_TEMPLATE, deletedTask, taskSize);
        } catch (IndexOutOfBoundsException exception) {
            return String.format(INDEX_ERROR_MESSAGE_TEMPLATE, this.index + TextUi.INDEX_OFFSET);
        }
    }

    public static Command parseArguments(String arguments) {
        if (arguments != null && !arguments.trim().isEmpty()) {
            try {
                return new DeleteCommand(Integer.parseInt(arguments.trim()) - TextUi.INDEX_OFFSET);
            } catch (NumberFormatException exception) {
                return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
            }
        } else {
            return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
        }
    }
}
