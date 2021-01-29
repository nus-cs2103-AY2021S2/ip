package duke.command;

import duke.exception.InvalidDescriptionException;
import duke.logging.Storage;
import duke.logging.TaskList;
import duke.logging.Ui;

public class FindCommand extends Command {
    public FindCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.find(taskDescription, ui);
        } catch (InvalidDescriptionException ex) {
            System.out.println("     " + ex.getMessage());
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
