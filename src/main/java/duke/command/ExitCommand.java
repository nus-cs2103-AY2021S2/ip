package duke.command;


import duke.ui.Ui;

/**
 * Sub-class of command that represents and execute the "bye" instruction of user.
 */
public class ExitCommand extends Command {
    public ExitCommand(String task, String date) {
        super("bye", task, date, true, command -> handleBye());
    }


    private static String handleBye() {
        return Ui.FAREWELL;
    }

}
