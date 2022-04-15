package chadbot.subfiles;

import chadbot.command.AddCommand;
import chadbot.command.Command;
import chadbot.command.DeleteCommand;
import chadbot.command.DoneCommand;
import chadbot.command.EditCommand;
import chadbot.command.ExitCommand;
import chadbot.command.FindCommand;
import chadbot.command.HelpCommand;
import chadbot.command.PrintCommand;
import chadbot.command.SortCommand;
import chadbot.command.StatsCommand;

/**
 * The Parser class parses the user input as a Command.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class Parser {

    /**
     * Parses the user input as either an ExitCommand, PrintCommand, FindCommand, DoneCommand,
     * DeleteCommand, SortCommand, HelpCommand, or AddCommand, based on the user input.
     *
     * @param s The user input.
     * @return A Command, whose type is based on the user input.
     */
    public static Command parse(String s) {
        String splitRegex = " ";
        String[] sArray = s.split(splitRegex);

        switch (sArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintCommand(s);
        case "find":
            return new FindCommand(s);
        case "done":
            return new DoneCommand(s);
        case "edit":
            return new EditCommand(s);
        case "delete":
            return new DeleteCommand(s);
        case "sort":
            return new SortCommand(s);
        case "help":
            return new HelpCommand(s);
        case "stats":
            return new StatsCommand();
        default:
            return new AddCommand(s);
        }
    }

}
