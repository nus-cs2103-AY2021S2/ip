package duke.models;

import java.util.List;
import java.util.Optional;

import duke.exceptions.DukeCommandNotFoundException;

public class Parser {
    private Optional<String> command;
    private Optional<List<String>> commandArgs;
    private Optional<List<String>> fullCommand;

    /**
     * Initialises Command object by taking first item of the list as the command and the rest of
     * the itemms of the string gets taken as the command arguments Null safety is enforced through
     * use of Optionals
     */
    public Parser(List<String> fullCommand) {
        this.command = Optional.ofNullable(fullCommand.get(0));
        this.commandArgs = Optional.of(fullCommand.subList(1, fullCommand.size()));
        this.fullCommand = Optional.of(fullCommand);
    }

    /**
     * Gets the first String passed in from the list of inputs, which is the Command in the input
     * line
     *
     * @return Commands enum depicting which command is to be executed by the bot.
     * @throws DukeCommandNotFoundException when the command is blank
     */
    public Commands getCommand() throws DukeCommandNotFoundException {
        return this.command.map(commandString -> {
            //@formatter:off
            switch (commandString) {
            case "done":
                return Commands.DONE;
            case "list":
                return Commands.LIST;
            case "todo":
                return Commands.TODO;
            case "event":
                return Commands.EVENT;
            case "deadline":
                return Commands.DEADLINE;
            case "delete":
                return Commands.DELETE;
            case "find":
                return Commands.FIND;
            case "bye":
                return Commands.BYE;
            default:
                return Commands.UNKNOWN;
            }
        }).orElseThrow(() -> new DukeCommandNotFoundException("No command was supplied from input."));
    }

    /**
     * Gets the remaining arguments passed into the command in the terminal as the arguments of the
     * command
     *
     * @return List of Strings which contains the rest of the arguments passed into the terminal
     * @throws DukeCommandNotFoundException when command is blank
     */
    public List<String> getCommandArgs() throws DukeCommandNotFoundException {
        return this.commandArgs.orElseThrow(() -> new DukeCommandNotFoundException(
                "No command arguments were supplied from input."));
    }

    /**
     * Used for level 1 to get full argument passed into the CLI, where todo, events and deadline
     * were yet to be implemented
     *
     * @return List of Strings which contains the rest of the arguments passed into the terminal
     * @throws DukeCommandNotFoundException when the command is blank
     */
    public List<String> getFullCommand() throws DukeCommandNotFoundException {
        return this.fullCommand.orElseThrow(() -> new DukeCommandNotFoundException(
                "No command arguments were supplied from input."));
    }
}
