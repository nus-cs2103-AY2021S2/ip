package duke.command;

import duke.Parser;

public class UpdateCommand {
    /**
     * handles the user input to mark the appropriate task as done
     * @param input user input of the task that is meant to be marked done
     * @return response string to user when marking tasks as done
     */
    public static String runCommand(String input) {
        String[] spiltInput = input.split("\\s+");
        int taskNumber = Integer.parseInt(spiltInput[1]);
        return Parser.markDone(taskNumber);

    }
}
