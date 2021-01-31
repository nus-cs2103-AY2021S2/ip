package helper;

import helper.command.*;

public class Parser {

    private Command parseAddsAndDeletes(String s) {
        return null;
    }

    private String getFirstWord(String s) {
        int index = s.indexOf(' ');
        if (index > -1) {
            return s.substring(0, index);
        } else {
            return s;
        }
    }

    public Command parse(String stringCommand) throws DukeException {
        String[] strings = stringCommand.split(" ", 2);
        String firstWord = strings[0];
        try {
            switch (firstWord) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "done":
                    return new DoneCommand(strings[1]);
                case "delete":
                    return new DeleteCommand(strings[1]);
                case "todo":
                    return new AddCommand(firstWord,strings[1]);
                case "event":
                    return new AddCommand(firstWord,strings[1]);
                case "deadline":
                    return new AddCommand(firstWord,strings[1]);
                default:
                    throw new DukeException("Weird keyword...");
            }
        } catch (Exception e) {
            throw new DukeException("Invalid Command");
        }
    }

}
