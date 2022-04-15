package duke.command;

import duke.Parser;

public class DeleteCommand {
    /**
     * Handles the user input and deletes the appropriate task accordingly
     * @param input user input
     * @return the response string upon successfully deleting a task
     */
    public static String runCommand(String input) {
        String[] spiltInput = input.split("\\s+");
        int taskNumber = Integer.parseInt(spiltInput[1]);
        return Parser.deleteTask(taskNumber);
    }
}
