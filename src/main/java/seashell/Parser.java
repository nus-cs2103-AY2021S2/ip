package seashell;

import seashell.CommandType;

public class Parser {

    public CommandType parse(String input) {
        if (input.stripTrailing().equals("bye")) {
            return CommandType.EXIT;
        } else if (input.stripTrailing().equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("done ")) {
            return CommandType.DONE;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.stripTrailing().equals("help")) {
            return CommandType.HELP;
        } else if (input.stripTrailing().equals("clear")) {
            return CommandType.CLEAR;
        } else {
            return CommandType.INVALID;
        }
    }

}
