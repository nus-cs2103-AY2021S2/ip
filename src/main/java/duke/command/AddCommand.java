package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        //if (fullCommand.)
        //taskList.add();
        storage.saveTasksToFile(taskList);
        return "Bye";
    }




}
