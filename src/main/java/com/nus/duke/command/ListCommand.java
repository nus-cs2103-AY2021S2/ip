package com.nus.duke.command;

import com.nus.duke.data.Task;

public class ListCommand extends Command {

    public static final String COMMAND = "list";
    public static final String NO_TASK_MESSAGE = "You have no tasks";
    public static final String USAGE_MESSAGE = COMMAND + ": List all the tasks\n"
            + "Usage: list";

    @Override
    public String execute() {
        if (this.taskList.getSize() == 0) {
            return NO_TASK_MESSAGE;
        } else {
            final StringBuilder builder = new StringBuilder();
            builder.append("Here are the tasks in your list:\n");
            int displayIndex = 1;
            for (Task task : this.taskList.getList()) {
                builder.append(displayIndex).append(": ").append(task).append("\n");
                displayIndex++;
            }
            return builder.toString().trim();
        }
    }
}
