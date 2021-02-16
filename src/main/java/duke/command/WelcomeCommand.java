package duke.command;

import duke.Storage;

import duke.task.TaskList;

/**
 * Represents a command telling duke to print the welcome message
 */
public class WelcomeCommand implements Command {

    public boolean isExit() {
        return false;
    }

    public String getResponString(TaskList tasks, Storage storage) {
        String welcomeResponse = "Hello! I'm Duke\nWhat can I do for you?";
        return welcomeResponse;
    }

}
