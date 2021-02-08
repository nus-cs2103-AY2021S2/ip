package duke.command;

import duke.TaskList;
/**
 * Represents a command that displays a string of text to help the user.
 */
public class HelpCommand implements Command {

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    @Override
    public String getResponse() {
        return "\nHere are a list of commands you can tell me:"
                + "\n======================="
                + "\n'list'"
                + "\nTo list the current tasks on your list"
                + "\n\n'todo <description>'"
                + "\nTo add a basic ToDo task to your list"
                + "\n\n'deadline <description> /by YYYY-MM-DD'"
                + "\nTo add a Deadline task with date specified"
                + "\n\n'event <description> /at YYYY-MM-DD AAAA-BBBB'"
                + "\nTo add an Event task from time AAAA to BBBB"
                + "\n\n'delete <task number>'"
                + "\nTo delete a task from your list"
                + "\n\n'bye'"
                + "\nExits the program";
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    @Override
    public TaskList execute(TaskList taskList) {
        return taskList;
    }

}
