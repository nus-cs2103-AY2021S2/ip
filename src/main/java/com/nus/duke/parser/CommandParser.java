package com.nus.duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nus.duke.command.Command;
import com.nus.duke.command.DeadlineCommand;
import com.nus.duke.command.DeleteCommand;
import com.nus.duke.command.DoneCommand;
import com.nus.duke.command.EventCommand;
import com.nus.duke.command.ExitCommand;
import com.nus.duke.command.FindCommand;
import com.nus.duke.command.HelpCommand;
import com.nus.duke.command.IncorrectCommand;
import com.nus.duke.command.ListCommand;
import com.nus.duke.command.TodoCommand;

public class CommandParser {

    /**
     * Used for initial separation of command and arguments.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<command>\\S+)(?<arguments>.*)");
    public static final String IMPROPER_FORMAT_MESSAGE = "Improper command format";

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(IMPROPER_FORMAT_MESSAGE);
        }

        final String command = matcher.group("command");
        final String arguments = matcher.group("arguments");

        switch (command.toLowerCase()) {
        case ListCommand.COMMAND:
            return new ListCommand();
        case ExitCommand.COMMAND:
            return new ExitCommand();
        case TodoCommand.COMMAND:
            return TodoCommand.parseArguments(arguments);
        case DeadlineCommand.COMMAND:
            return DeadlineCommand.parseArguments(arguments);
        case EventCommand.COMMAND:
            return EventCommand.parseArguments(arguments);
        case DoneCommand.COMMAND:
            return DoneCommand.parseArguments(arguments);
        case DeleteCommand.COMMAND:
            return DeleteCommand.parseArguments(arguments);
        case FindCommand.COMMAND:
            return FindCommand.parseArguments(arguments);
        case HelpCommand.COMMAND:
            return new HelpCommand();
        default:
            return new HelpCommand(command);
        }
    }
}
