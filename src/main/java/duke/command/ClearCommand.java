package duke.command;

import duke.utils.Statistics;

/**
 * ClearCommand that when executes clear the Past history of tasks.
 */
public class ClearCommand extends Command{
    private static final String NO_TASK = "";
    private static final String NO_DATE = "";

    /**
     * Create a ClearCommand object that return a response when executed.
     */
    public ClearCommand() {
        super("clear", NO_TASK, NO_DATE, false, command -> handleClearCommand());
    }

    private static String handleClearCommand() {
        Statistics.clear();
        return "Successfully Clear Statistics";
    }
}
