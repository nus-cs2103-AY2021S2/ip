package commands;

import java.io.IOException;

import data.TaskList;
import data.Todo;
import ui.TextUi;

public class AddTodoCommand extends Command {
    public static final String COMMAND_TEXT = "todo";

    private Todo todo;

    public AddTodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Adds todo to given tasks and outputs corresponding acknowledgement message
     * @param tasks
     * @param ui
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        tasks.add(todo);
        ui.writeAddTask(todo, tasks);
    }
}
