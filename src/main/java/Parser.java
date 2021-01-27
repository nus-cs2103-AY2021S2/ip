public class Parser {
    public Command parseCmd(String rawInput) {
        String cmd = rawInput.trim();

        try {
            if (ExitCommand.isExitCommand(cmd)) {
                return new ExitCommand();
            } else if (AddToDoCommand.isAddToDoCommand(cmd)) {
                return AddToDoCommand.parseAddToDoCommand(cmd);
            } else if (AddDeadlineCommand.isAddDeadlineCommand(cmd)) {
                return AddDeadlineCommand.parseAddDeadlineCommand(cmd);
            } else if (AddEventCommand.isAddEventCommand(cmd)) {
                return AddEventCommand.parseAddEventCommand(cmd);
            } else if (ListCommand.isListCommand(cmd)) {
                return ListCommand.parseListCommand(cmd);
            } else if (DoneCommand.isDoneCommand(cmd)) {
                return DoneCommand.parseDoneCommand(cmd);
            } else if (DeleteCommand.isDeleteCommand(cmd)) {
                return DeleteCommand.parseDeleteCommand(cmd);
            } else {
                return new InvalidCommand(String.format("Sorry, I don't know what '%s' means", cmd));
            }
        } catch (DukeException ex) {
            return new InvalidCommand(ex.getMessage());
        }
    }
}
