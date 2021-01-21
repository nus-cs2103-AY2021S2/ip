public interface Command {
    /**
     * Checks if chatbot should exit after command
     *
     * @return value of isExitCommand in Command objects
     */
    boolean isExitCommand();

    /**
     * Executes the command on the taskList
     *
     * @return resultant tasklist after command is run
     */
    TaskList runCommand(TaskList taskList);

    /**
     * Gets the response of the command
     *
     * @return String response of the command
     */
    String getResponse();
}