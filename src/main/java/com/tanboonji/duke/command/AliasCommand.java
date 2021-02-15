package com.tanboonji.duke.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.exception.InvalidCommandArgumentException;

/**
 * The AliasCommand class contains information to execute the "alias" command.
 */
public class AliasCommand extends Command {

    /** String input to execute this command */
    public static final String COMMAND = "alias";
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^\\W*(\\S+)\\W*(\\S+)$");
    private static final String ERROR_MESSAGE =
            "Sorry, please enter a valid command and alias\n"
                    + "\tCommand: alias [command] [alias]";
    private static final String SUCCESS_MESSAGE = "You have aliased %s with %s\n";
    private static final int COMMAND_GROUP = 1;
    private static final int ALIAS_GROUP = 2;

    private final String alias;
    private final String command;

    /**
     * Default class constructor.
     */
    public AliasCommand(String command, String alias) {
        this.command = command;
        this.alias = alias;
    }

    @Override
    public boolean shouldSaveData() {
        return true;
    }

    @Override
    public boolean shouldExitDuke() {
        return false;
    }

    @Override
    public String execute() {
        aliasMap.put(alias, command);
        return String.format(SUCCESS_MESSAGE, command, alias);
    }

    /**
     * Returns new alias command after parsing command arguments.
     *
     * @param arguments Command arguments.
     * @return New alias command.
     * @throws DukeException If user input does not match alias command format.
     */
    public static AliasCommand parseArguments(String arguments) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            throw new InvalidCommandArgumentException(ERROR_MESSAGE);
        }

        String command = matcher.group(COMMAND_GROUP);
        String alias = matcher.group(ALIAS_GROUP);

        return new AliasCommand(command, alias);
    }
}
