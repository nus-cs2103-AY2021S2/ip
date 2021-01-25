package duke.parser;

import duke.commands.*;

public class Parser {
    public Parser() {}

    public static Command parse(String fullCommand) {
        Command command;
        if (fullCommand.equals("bye")) {
            command = new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else {
            String[] splitCommands = fullCommand.split(" ");
            if (splitCommands[0].equals("done")) {
                command = new DoneCommand(splitCommands);
            } else if (splitCommands[0].equals("delete")) {
                command = new DeleteCommand(splitCommands);
            } else {
                command = new AddCommand(fullCommand, splitCommands);
            }
        }
        return command;
    }
}
