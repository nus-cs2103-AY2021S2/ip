package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    private Todo todo;

    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = ui.getAddMessage(tasks, tasks.addTask(todo));
            storage.save(tasks);
            return message;
        } catch (NumberFormatException ex) {
            return "Please enter integer values..";
        }
    }
}
