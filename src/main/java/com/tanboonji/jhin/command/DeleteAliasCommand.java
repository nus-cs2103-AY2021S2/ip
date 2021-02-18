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
    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the delete alias command you entered is invalid.\n"
                    + "Please enter a valid delete alias command in the following format:\n"
                    + "[deletealias, rmalias] <alias>";
    private static final String SUCCESS_MESSAGE_FORMAT = "Noted! I've removed this alias:\n"
            + "%s";
    private static final String INVALID_ALIAS_MESSAGE_FORMAT = "Sorry, I couldn't find the alias '%s' to delete.\n"
            + "Please enter a valid alias and try again.";

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
            return String.format(INVALID_ALIAS_MESSAGE_FORMAT, alias);
        }

        String deletedAlias = aliasMap.deleteAlias(alias);
        return String.format(SUCCESS_MESSAGE_FORMAT, deletedAlias);
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
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        return new DeleteAliasCommand(arguments.trim());
    }
}
