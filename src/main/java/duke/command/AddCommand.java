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
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + taskToBeAdded);
        tasks.add(taskToBeAdded);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            System.out.println("Error in loading storage from addCommand.execute...Check data/duke.txt");
            this.isExit = true;
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

}
