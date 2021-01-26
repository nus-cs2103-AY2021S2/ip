package main.java.duke.subfiles;

import main.java.duke.command.AddCommand;
import main.java.duke.command.Command;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.ExitCommand;
import main.java.duke.command.FindCommand;
import main.java.duke.command.PrintCommand;

public class Parser {

    /**
     * Calls the task manager to perform either task addition,
     * deletion, printing, or marking as done, based on the
     * user input. Also terminates the program if a terminating
     * input is supplied by the user.
     *
     * @param s A user input.
     * @return True if the user input is not a terminating
     *         input, and false otherwise.
     */
    public static Command parse(String s) {
        String[] sArray = s.split(" ");

        switch (sArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new PrintCommand(s);
        case "find":
            return new FindCommand(s);
        case "done":
            return new DoneCommand(s);
        case "delete":
            return new DeleteCommand(s);
        default:
            return new AddCommand(s);
        }
    }
}
