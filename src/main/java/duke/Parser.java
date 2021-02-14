package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandName;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.StatsCommand;
import duke.command.TodoCommand;
import duke.ui.Ui;

/**
 *  The Parser class encapsulates methods to handle user commands.
 */
public class Parser {
    private Ui ui = new Ui();

    /**
     * Returns a Command corresponding to the command the user input.
     *
     * @param fullCmd The full user input string.
     * @return Command corresponding to user input.
     * @throws DukeException if the user input a command with invalid format.
     */
    public Command parse(String fullCmd) throws DukeException {
        assert fullCmd != null : "Command string cannot be null";

        String[] fullCmdStrArray = fullCmd.split(" ");
        Command toRun = null;
        CommandName cmd;
        try {
            cmd = CommandName.valueOf(fullCmdStrArray[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException(ui.invalidCmdError());
        }
        switch(cmd) {
        case LIST:
            return new ListCommand(fullCmd, ui);
        case DONE:
            return new DoneCommand(fullCmd, ui);
        case TODO:
            return new TodoCommand(fullCmd, ui);
        case EVENT:
            return new EventCommand(fullCmd, ui);
        case DEADLINE:
            return new DeadlineCommand(fullCmd, ui);
        case DELETE:
            return new DeleteCommand(fullCmd, ui);
        case FIND:
            return new FindCommand(fullCmd, ui);
        case STATS:
            return new StatsCommand(fullCmd, ui);
        case BYE:
            return new ByeCommand(fullCmd, ui);
        default:
            throw new DukeException(ui.invalidCmdError());
        }
    }

    /**
     * Checks whether a string is a number in String type.
     *
     * @param str The string to be checked.
     * @return True if the string is a number in String type, false otherwise.
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
