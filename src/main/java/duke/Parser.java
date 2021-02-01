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
import duke.command.TodoCommand;

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
        String[] fullCmdStrArray = fullCmd.split(" ");
        Command toRun = null;
        CommandName cmd;
        try {
            cmd = CommandName.valueOf(fullCmdStrArray[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry human, I have not been trained to process that command.");
        }
        switch(cmd) {
        case LIST:
            toRun = new ListCommand(fullCmd, ui);
            break;
        case DONE:
            toRun = new DoneCommand(fullCmd, ui);
            break;
        case TODO:
            toRun = new TodoCommand(fullCmd, ui);
            break;
        case EVENT:
            toRun = new EventCommand(fullCmd, ui);
            break;
        case DEADLINE:
            toRun = new DeadlineCommand(fullCmd, ui);
            break;
        case DELETE:
            toRun = new DeleteCommand(fullCmd, ui);
            break;
        case FIND:
            toRun = new FindCommand(fullCmd, ui);
            break;
        case BYE:
            toRun = new ByeCommand(fullCmd, ui);
            break;
        default:
            throw new DukeException("Sorry human, I have not been trained to process that command.");
        }
        return toRun;
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
