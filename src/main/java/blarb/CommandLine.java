package blarb;

/**
 * {@code CommandLine} is a object the stores the entire inputted command.
 */
class CommandLine {
    final Command command;
    final String description;

    /**
     * Initializes a new {@code CommandLine}.
     *
     * @param command     The main command.
     * @param description The description of the main command.
     */
    public CommandLine(Command command, String ...description) {
        this.command = command;
        if (description.length > 0) {
            this.description = description[0];
        } else {
            this.description = "";
        }

    }

    /**
     * Checks if the command is a single command without descriptions.
     *
     * @return If the command is a single command.
     */
    public boolean isSingleCommand() {
        return description.isEmpty();
    }
}
