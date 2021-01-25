package main.java.subfiles;

import main.java.command.AddCommand;
import main.java.command.Command;
import main.java.command.DeleteCommand;
import main.java.command.DoneCommand;
import main.java.command.ExitCommand;
import main.java.command.PrintCommand;

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
        case "done":
            return new DoneCommand(sArray[1]);
        case "delete":
            return new DeleteCommand(sArray[1]);
        default:
            return new AddCommand(s);
        }
    }
}
