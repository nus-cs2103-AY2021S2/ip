package duke.Command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.Task;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task taskToBeAdded;

    public AddCommand(Task tasktoBeAdded){
        this.taskToBeAdded = tasktoBeAdded;
    }

    @Override
    public boolean execute (Ui ui, TaskList tasks, Storage storage) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + taskToBeAdded);
        tasks.add(taskToBeAdded);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            System.out.println("Error in loading storage from addCommand.execute...Check data/duke.txt");
            return EXECUTION_FAIL;
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        return EXECUTION_SUCCESS;
    }

}
