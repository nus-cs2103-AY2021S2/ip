package duke;

import duke.task.AbstractTaskFactory;
import duke.task.Task;

class AddCommand implements ICommand {
    private TaskList tasks;
    private AbstractTaskFactory taskFactory;

    AddCommand(TaskList tasks, AbstractTaskFactory taskFactory) {
        this.tasks = tasks;
        this.taskFactory = taskFactory;
    }

    @Override
    public void execute(String parameters) {
        try {
            Task newTask = taskFactory.getTask(parameters);
            this.tasks.addTask(newTask);
            System.out.println(newTask.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
