package duke.command;

public enum CommandOption {
    LIST("list");

    private final String commandWord;

    CommandOption(String commandWord) {
        this.commandWord = commandWord;
    }
}
