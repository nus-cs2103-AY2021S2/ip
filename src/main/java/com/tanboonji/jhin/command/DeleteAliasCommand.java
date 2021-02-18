package com.tanboonji.jhin.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;

/**
 * The DeleteAliasCommand class contains information to execute the "delete" command.
 */
public class DeleteAliasCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("^\\W*(\\S+)\\W*$");
    private static final String ERROR_MESSAGE = "Sorry, please enter a valid alias.\n"
            + "Command: deletealias [alias]";
    private static final String SUCCESS_MESSAGE = "Noted! I've removed this alias:\n"
            + "%s";
    private static final String SUCCESS_NO_MATCH_MESSAGE = "Sorry, I couldn't find the alias '%s' to delete.";

    private final String alias;

    private DeleteAliasCommand(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitJhin() {
        return false;
    }

    @Override
    public String execute() {
        if (!aliasMap.containsAlias(alias)) {
            return String.format(SUCCESS_NO_MATCH_MESSAGE, alias);
        }

        String deletedAlias = aliasMap.deleteAlias(alias);
        return String.format(SUCCESS_MESSAGE, deletedAlias);
    }

    /**
     * Returns new delete alias command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New delete alias command.
     * @throws JhinException If alias does not exist.
     */
    public static DeleteAliasCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }

        return new DeleteAliasCommand(arguments.trim());
    }
}
