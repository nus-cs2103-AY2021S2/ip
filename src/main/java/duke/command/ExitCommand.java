package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Encapsulates the information of an parsed exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public String execute(TaskList tasks, Storage storage) {
        return "Bye!";
    }

    public boolean isExit() {
        return true;
    }
}
