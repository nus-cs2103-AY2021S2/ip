package duke.command;

import duke.TaskList;

public class ExitCommand implements ICommand {
    private TaskList tasks;

    public ExitCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute(String parameters) {
        tasks.setExited();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
