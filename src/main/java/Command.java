public interface Command {
    /**
     * Checks if chatbot should exit after command
     * @return value of isExitCommand in Command objects
     */
    boolean isExitCommand();

    /**
     * Executes the command
     * @return Result of the command after being run
     */
    String runCommand();
}
