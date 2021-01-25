package duke.commands;

import duke.exceptions.*;

public enum DukeCommand {

    BYE,
    LIST,
    DONE,
    DELETE,
    EVENT,
    TODO,
    DEADLINE;

    public static DukeCommand getCommand(String cmd) throws DukeExceptionCommandNotFound {
        if (cmd.indexOf(" ") != -1) {
            cmd = cmd.substring(0, cmd.indexOf(' '));
        }
        cmd = cmd.toLowerCase();

        if (cmd.equals("bye")) {
            return BYE;
        } else if (cmd.equals("list")) {
            return LIST;
        } else if (cmd.equals("done")) {
            return DONE;
        } else if (cmd.equals("delete")) {
            return DELETE;
        } else if (cmd.equals("event")) {
            return EVENT;
        } else if (cmd.equals("todo")) {
            return TODO;
        } else if (cmd.equals("deadline")) {
            return DEADLINE;
        } else {
            throw new DukeExceptionCommandNotFound("Command '" + cmd + "' is invalid.");
        }
    }

    public static String getArgument(String line) {
        int idx = line.indexOf(' ');
        if (idx == -1) {
            return "";
        }
        return line.substring(line.indexOf(' ')+1);
    }

    public static DukeCommand getCommandFromString(String cmd) throws DukeExceptionCommandNotFound {
        switch (cmd) {
        case "E":
            return EVENT;
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        default:
            throw new DukeExceptionCommandNotFound("Command '" + cmd + "' is invalid.");
        }
    }
}
