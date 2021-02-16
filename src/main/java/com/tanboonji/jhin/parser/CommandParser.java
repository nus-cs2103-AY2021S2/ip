package com.tanboonji.jhin.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tanboonji.jhin.command.AliasCommand;
import com.tanboonji.jhin.command.ByeCommand;
import com.tanboonji.jhin.command.Command;
import com.tanboonji.jhin.command.DeadlineCommand;
import com.tanboonji.jhin.command.DeleteAliasCommand;
import com.tanboonji.jhin.command.DeleteCommand;
import com.tanboonji.jhin.command.DoneCommand;
import com.tanboonji.jhin.command.EventCommand;
import com.tanboonji.jhin.command.FindCommand;
import com.tanboonji.jhin.command.HelpCommand;
import com.tanboonji.jhin.command.ListAliasCommand;
import com.tanboonji.jhin.command.ListCommand;
import com.tanboonji.jhin.command.ToDoCommand;
import com.tanboonji.jhin.exception.InvalidCommandException;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.AliasMap;

/**
 * The CommandParser class helps to parse user string input into respective intended command.
 */
public class CommandParser {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("\\W*(\\S+)\\W*(.*)");
    private static final String INVALID_COMMAND_MESSAGE = "Sorry, please enter a valid command.\n";
    private static final int COMMAND_GROUP = 1;
    private static final int ARGUMENTS_GROUP = 2;

    /**
     * Parses input from alias to full actual command.
     * Replace alias with full actual command if alias is found, else return input as it is.
     *
     * @param input User string input.
     * @param aliasMap Alias hash map.
     * @return Full actual command.
     */
    public static String parseAlias(String input, AliasMap aliasMap) {
        Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            return input;
        }

        String command = matcher.group(COMMAND_GROUP).trim();
        String arguments = matcher.group(ARGUMENTS_GROUP).trim();

        if (aliasMap.containsAlias(command)) {
            command = aliasMap.getAlias(command);
        }

        return command + " " + arguments;
    }

    /**
     * Parses input from String class to Command class.
     * Returns respective intended command if input is successfully parsed, else returns invalid command.
     *
     * @param input User string input.
     * @return Respective intended command with arguments or invalid command.
     * @throws JhinException If any error occurs while parsing command from String class to Command class.
     */
    public static Command parseCommand(String input) throws JhinException {
        Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
        }

        String command = matcher.group(COMMAND_GROUP).trim();
        String arguments = matcher.group(ARGUMENTS_GROUP).trim();

        switch (command.toLowerCase()) {
        case ToDoCommand.COMMAND:
            return ToDoCommand.parseArguments(arguments);
        case EventCommand.COMMAND:
            return EventCommand.parseArguments(arguments);
        case DeadlineCommand.COMMAND:
            return DeadlineCommand.parseArguments(arguments);
        case ListCommand.COMMAND:
            return new ListCommand();
        case DoneCommand.COMMAND:
            return DoneCommand.parseArguments(arguments);
        case DeleteCommand.COMMAND:
            return DeleteCommand.parseArguments(arguments);
        case HelpCommand.COMMAND:
            return new HelpCommand();
        case ByeCommand.COMMAND:
            return new ByeCommand();
        case FindCommand.COMMAND:
            return new FindCommand(arguments);
        case AliasCommand.COMMAND:
            return AliasCommand.parseArguments(arguments);
        case DeleteAliasCommand.COMMAND:
            return DeleteAliasCommand.parseArguments(arguments);
        case ListAliasCommand.COMMAND:
            return new ListAliasCommand();
        default:
            throw new InvalidCommandException(INVALID_COMMAND_MESSAGE);
        }
    }
}
