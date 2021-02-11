package duke.command;

import duke.Parser;

public class ReadCommand {
    public static String runCommand() {
        return Parser.listTask();
    }
}
