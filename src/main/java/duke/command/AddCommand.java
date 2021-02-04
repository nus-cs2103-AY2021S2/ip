package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    private final Task taskToBeAdded;

    public AddCommand(Task tasktoBeAdded) {
        this.taskToBeAdded = tasktoBeAdded;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.add(taskToBeAdded);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            System.out.println("Error in loading storage from addCommand.execute...Check data/duke.txt");
            this.isExit = true;
        }
        String displayMessage = "Got it. I've added this task:\n\t" + taskToBeAdded + "\n"
                + Ui.getDisplayOfNumberOfTasks(tasks);
        ui.setDisplayMessage(displayMessage);
    }

}
