package duke.command;


import duke.ui.Ui;

/**
 * Sub-class of command that represents and executes the "bye" instruction of user.
 */
public class ExitCommand extends Command {
    private static final String NO_TASK = "";
    private static final String NO_DATE = "";
    /**
     * Creates an ExitCommand object that exits the program when executed.
     */
    public ExitCommand() {
        super("bye", NO_TASK, NO_DATE, true, command -> handleBye());
    }


    private static String handleBye() {
        return Ui.FAREWELL;
    }

}
