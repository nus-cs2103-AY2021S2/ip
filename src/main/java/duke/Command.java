package duke;

/**
 * The Command class represents a command to be done by ChaeLisa. A command has 2 main components:
 * the command name: commandTitle
 * the command body: commandContent
 */
@SuppressWarnings("checkstyle:Regexp")
class Command {
    private final String commandTitle;
    private final String commandContent;

    /**
     * Returns a command with the specified name and body
     *
     * @param commandTitle   the command name
     * @param commandContent the command body
     */
    public Command(String commandTitle, String commandContent) {
        this.commandTitle = commandTitle;
        this.commandContent = commandContent;
    }

    /**
     * Returns the content of a command.
     *
     * @return the content of a command
     */
    public String getCommandContent() {
        return commandContent;
    }

    /**
     * Returns the name (title) of the command.
     *
     * @return the name (title) of the command
     */
    public String getCommandTitle() {
        return commandTitle;
    }
}
