package com.nus.duke.command;

import com.nus.duke.data.Task;
import com.nus.duke.ui.TextUi;

public class DoneCommand extends Command {

    public static final String COMMAND = "done";
    public static final String IMPROPER_USAGE_FORMAT = "Improper command format";
    public static final String USAGE_MESSAGE = COMMAND + ": Marks a task as done\n"
            + "Usage: done [index]\n"
            + "Example: done 3";
    public static final String SUCCESS_MESSAGE_TEMPLATE = "Nice! I've marked this task as done\n%s";
    public static final String INDEX_ERROR_MESSAGE_TEMPLATE = "Invalid index number %d";

    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        try {
            Task doneTask = this.taskList.markAsDone(this.index);
            return String.format(SUCCESS_MESSAGE_TEMPLATE, doneTask);
        } catch (IndexOutOfBoundsException exception) {
            return String.format(INDEX_ERROR_MESSAGE_TEMPLATE, this.index + TextUi.INDEX_OFFSET);
        }
    }

    public static Command parseArguments(String arguments) {
        if (arguments != null && !arguments.trim().isEmpty()) {
            try {
                return new DoneCommand(Integer.parseInt(arguments.trim()) - TextUi.INDEX_OFFSET);
            } catch (NumberFormatException exception) {
                return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
            }
        } else {
            return new IncorrectCommand(IMPROPER_USAGE_FORMAT + "\n" + USAGE_MESSAGE);
        }
    }
}
