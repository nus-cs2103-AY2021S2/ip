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

    @Override
    public void execute(TaskList tasks, TextUi ui) throws IOException {
        tasks.add(todo);
        ui.writeAddTask(todo, tasks);
    }
}
