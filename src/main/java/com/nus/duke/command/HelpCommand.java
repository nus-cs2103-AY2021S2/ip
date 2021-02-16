package com.nus.duke.command;

public class HelpCommand extends Command {

    public static final String COMMAND = "help";
    private final String unknownCommand;

    public HelpCommand(String unknownCommand) {
        this.unknownCommand = unknownCommand;
    }

    public HelpCommand() {
        this.unknownCommand = null;
    }

    @Override
    public String execute() {
        if (this.unknownCommand == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(TodoCommand.USAGE_MESSAGE).append("\n\n")
                    .append(DeadlineCommand.USAGE_MESSAGE).append("\n\n")
                    .append(EventCommand.USAGE_MESSAGE).append("\n\n")
                    .append(DoneCommand.USAGE_MESSAGE).append("\n\n")
                    .append(DeleteCommand.USAGE_MESSAGE).append("\n\n")
                    .append(ListCommand.USAGE_MESSAGE);
            return builder.toString().trim();
        } else {
            return "Unknown command: " + this.unknownCommand;
        }
    }
}
