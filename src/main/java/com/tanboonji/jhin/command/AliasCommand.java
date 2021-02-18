package com.tanboonji.jhin.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.exception.InvalidCommandArgumentException;
import com.tanboonji.jhin.exception.JhinException;

/**
 * The AliasCommand class contains information to execute the "alias" command.
 */
public class AliasCommand extends Command {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(\\S+)\\W+(\\S+)$");
    private static final String INVALID_ARGUMENT_MESSAGE = "Sorry, the alias command you entered is invalid.\n"
                    + "Please enter a valid alias command in the following format:\n"
                    + "alias <alias> <command>";
    private static final String INVALID_COMMAND_MESSAGE_FORMAT =
            "Sorry, the command '%s' you entered is invalid.\n"
                    + "Please enter a valid command and try again.";
    private static final String INVALID_ALIAS_MESSAGE_FORMAT =
            "Sorry, the alias '%s' you entered is a default command.\n"
            + "Please enter another alias and try again.";
    private static final String SUCCESS_MESSAGE_FORMAT = "Got it. I've added this alias:\n"
            + "%s";
    private static final int ALIAS_GROUP = 1;
    private static final int COMMAND_GROUP = 2;

    private final String alias;
    private final String command;

    /**
     * Default class constructor.
     */
    public AliasCommand(String alias, String command) {
        this.alias = alias;
        this.command = command;
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
        String newAlias = aliasMap.addAlias(command, alias);
        return String.format(SUCCESS_MESSAGE_FORMAT, newAlias);
    }

    /**
     * Returns new alias command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New alias command.
     * @throws JhinException If arguments does not match alias command format or contains invalid arguments.
     */
    public static AliasCommand parseArguments(String arguments) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(INVALID_ARGUMENT_MESSAGE);
        }

        String alias = matcher.group(ALIAS_GROUP);
        String command = matcher.group(COMMAND_GROUP);

        if (Command.isCommandValid(alias)) {
            throw new InvalidCommandArgumentException(String.format(INVALID_ALIAS_MESSAGE_FORMAT, alias));
        }
        if (!Command.isCommandValid(command)) {
            throw new InvalidCommandArgumentException(String.format(INVALID_COMMAND_MESSAGE_FORMAT, command));
        }

        return new AliasCommand(alias, command);
    }
}
