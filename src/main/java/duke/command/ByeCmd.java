package duke.command;

import duke.task.TaskList;

/**
 * Represents the execution of Bye Command
 */
public class ByeCmd extends Command {
    private static final String BYE_MSG = "Bye. Hope to see you again soon!\n";

    /**
     * Returns true if processed command wants the bot to exit
     *
     * @return true if processed command wants the bot to exit
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns the response of the bot after execution of command
     *
     * @param lst TaskList
     * @return response
     */
    @Override
    public String execute(TaskList lst) {
        return BYE_MSG;
    }
}
