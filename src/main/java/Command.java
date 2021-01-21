/**
 * An interface for commands that the user inputs.
 */
interface Command {

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    public boolean shouldExit();

    /**
     * Gets the reply message.
     * @return The reply message from the bot.
     */
    public String getResponse();

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList);
}
