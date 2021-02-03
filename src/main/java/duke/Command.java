package duke;

/**
 * The Command class represents a command to be done by Danh's Duke. A command has 2 main components:
 * the command name: commandTitle
 * the command body: commandContent
 */
@SuppressWarnings("checkstyle:Regexp")
class Command {
    private String commandTitle;
    private String commandContent;

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
    public String getCommandContent() {
        return commandContent;
    }

    public String getCommandTitle() {
        return commandTitle;
    }
}
