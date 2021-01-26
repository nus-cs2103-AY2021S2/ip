package duke.commands;

import duke.Ui;
import duke.tasks.TaskList;
import duke.tasks.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(CommandType.ADD);
        this.task = task;
    }


    @Override
    public void excecute(TaskList list) {
        list.addTask(task);
        ui.printTask(task, list.size());
    }
}
