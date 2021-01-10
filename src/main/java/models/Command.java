package models;

import java.util.List;
import java.util.Optional;

public class Command {
    Optional<String> command;
    Optional<List<String>> commandArgs;
    Optional<List<String>> fullCommand;

    /**
     * Initialises Command object by taking first item of the list as the command
     * and the rest of the itemms of the string gets taken as the command arguments
     * Null safety is enforced through use of Optionals
     */
    public Command(List<String> fullCommand) {
        this.command = Optional.ofNullable(fullCommand.get(0));
        this.commandArgs = Optional.ofNullable(fullCommand.subList(1, fullCommand.size()));
        this.fullCommand = Optional.ofNullable(fullCommand);
    }

    /**
     * Gets the first String passed in from the list of inputs, which is the Command
     * in the input line
     * 
     * @return String depicting which command is to be executed by the bot.
     * @throws IllegalArgumentException
     */
    public String getCommand() throws IllegalArgumentException {
        return this.command.orElseThrow(() -> new IllegalArgumentException("No command was supplied from input."));
    }

    /**
     * Gets the remaining arguments passed into the command in the terminal as the
     * arguments of the command
     * 
     * @return List<String> which contains the rest of the arguments passed into the
     *         terminal
     * @throws IllegalArgumentException
     */
    public List<String> getCommandArgs() throws IllegalArgumentException {
        return this.commandArgs
                .orElseThrow(() -> new IllegalArgumentException("No command arguments were supplied from input."));
    }

    public List<String> getFullCommand() throws IllegalArgumentException {
        return this.fullCommand
                .orElseThrow(() -> new IllegalArgumentException("No command arguments were supplied from input."));
    }
}
