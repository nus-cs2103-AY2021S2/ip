package antonio.command;

import antonio.TaskList;

/**
 * Represents a find command
 */
public class FindCommand implements Command {

    private String key;
    private TaskList findList;

    /**
     * Constructor for find command.
     * @param key Key to be matched with finding.
     */
    public FindCommand(String key) {
        this.key = key;
    }

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
        return "\nL'ho trovata! Here are the matching tasks in your list:...\n" + findList.toString();
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    @Override
    public TaskList execute(TaskList taskList) {
        findList = taskList.findKeyWord(key);
        return taskList;
    }
}
