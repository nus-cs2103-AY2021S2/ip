package duke;

/**
 * The Command class represents a command to be done by Danh's Duke. A command has 2 main components:
 * the command name: commandTitle
 * the command body: commandContent
 */
class Command {
    public String commandTitle;
    public String commandContent;

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
}
