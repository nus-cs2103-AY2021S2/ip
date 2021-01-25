package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

public class UnknownCommand extends Command {
    public UnknownCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.unknownCommandInteraction();
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
