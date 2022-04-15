package duke.command;

import duke.Parser;

public class ReadCommand {
    /**
     * lists all the task currently in the task list
     * @return all the tasks in the list
     */
    public static String runCommand() {
        return Parser.listTask();
    }
}
