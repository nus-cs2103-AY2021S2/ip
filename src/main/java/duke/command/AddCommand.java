package duke.command;

import duke.TaskList;
import duke.task.AbstractTaskFactory;
import duke.task.Task;

/**
 * Command to add tasks into the taskList when executed.
 */
public class AddCommand implements ICommand {
    private TaskList tasks;
    private AbstractTaskFactory taskFactory;

    /**
     * Constructor method for the add command.
     *
     * @param tasks the taskList the task is being added into.
     * @param taskFactory Factory object responsible for creating the task
     */
    public AddCommand(TaskList tasks, AbstractTaskFactory taskFactory) {
        this.tasks = tasks;
        this.taskFactory = taskFactory;
    }

    /**
     * When called, it will use the given input as information to create the task
     * using the factory given and add it into the TaskList object.
     *
     * @param parameters input required by the Command.
     */
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
