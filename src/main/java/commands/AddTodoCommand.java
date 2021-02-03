package commands;

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
     * Adds todo to given tasks and returns corresponding acknowledgement message
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        tasks.add(todo);
        return ui.getAddTaskMessage(todo, tasks);
    }
}
