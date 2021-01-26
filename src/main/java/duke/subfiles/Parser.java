package main.java.duke.subfiles;

import main.java.duke.command.AddCommand;
import main.java.duke.command.Command;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.ExitCommand;
import main.java.duke.command.PrintCommand;

/**
 * The Parser class parses the user input as a Command.
 *
 * @author  arsatis
 * @version 1.1
 * @since   2021-01-26
 */
public class Parser {

    /**
     * Parses the user input as either an ExitCommand, PrintCommand,
     * DoneCommand, DeleteCommand, or AddCommand, based on the
     * user input.
     *
     * @param s The user input.
     * @return A Command, whose type is based on the user input.
     */
    public static Command parse(String s) {
        String[] sArray = s.split(" ");

        switch (sArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintCommand(s);
        case "done":
            return new DoneCommand(s);
        case "delete":
            return new DeleteCommand(s);
        default:
            return new AddCommand(s);
        }
    }

}
