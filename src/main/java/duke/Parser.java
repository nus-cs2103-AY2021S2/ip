package duke;

import duke.command.Command;
import duke.command.CommandName;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

public class Parser {
    private Ui ui = new Ui();

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
        default:
            throw new DukeException("Sorry human, I have not been trained to process that command.");
        }
        return toRun;
    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
