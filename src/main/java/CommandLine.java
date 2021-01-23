public class CommandLine {
    final Command command;
    final String description;

    public CommandLine(Command command, String description) {
        this.command = command;
        this.description = description;
    }

    public boolean isSingleCommand() {
        return description.isEmpty();
    }
}
