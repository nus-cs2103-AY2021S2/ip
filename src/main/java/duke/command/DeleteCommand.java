package duke.command;

import duke.Parser;

public class DeleteCommand {
    public static String runCommand(String input) {
        String[] spiltInput = input.split("\\s+");
        int taskNumber = Integer.parseInt(spiltInput[1]);
        return Parser.deleteTask(taskNumber);
    }
}
