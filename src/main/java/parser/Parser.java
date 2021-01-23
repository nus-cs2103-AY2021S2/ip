package parser;

import commands.*;

public class Parser {
    public Parser() {}

    public static Command parse(String fullCommand) {
        Command command;
        if (fullCommand.equals("bye")) {
            command = new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else {
            String[] check = fullCommand.split(" ");
            if (check[0].equals("done")) {
                command = new DoneCommand(check);
            } else if (check[0].equals("delete")) {
                command = new DeleteCommand(check);
            } else {
                command = new AddCommand(fullCommand, check);
            }
        }
        return command;
    }
}
