package lihua.commands;

import lihua.commands.feedback.CommandResult;
import lihua.commons.Messages;
import lihua.tasks.Task;

/**
 * Command class representing a command to add a task.
 * The task might be a todo, a deadline, or an event.
 */
public class AddCommand extends Command {
    /** Command help information for adding command */
    public static final String MESSAGE_USAGE =
            "todo/deadline/event: add a task of a specific type to the task list.\n"
                      + "---- Example 1: todo [task name]\n"
                      + "---- Example 2: deadline [task name] /by [yyyy-mm-dd]\n"
                      + "---- Example 3: event [task name] /at [yyyy-mm-dd]";
    private final Task toAdd;

    /**
     * Initializes a new AddCommand with a task.
     *
     * @param toAdd The task to be added to the list.
     */
    public AddCommand(Task toAdd) {
        super();
        this.toAdd = toAdd;
    }

    /**
     * Executes the adding command. Add the task into list and give feedback to user.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        try {
            tasks.addTask(toAdd);
            assert tasks.getSize() >= 1;
            String noun = tasks.getSize() <= 1 ? "task" : "tasks";
            String message = String.format("Got it. I have added this task to your list:\n---- %s\n"
                            + "Now you have %d %s in total. Good luck.", toAdd.toString(), tasks.getSize(), noun);
            return new CommandResult(message);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_REPORTING_ADDING_FAILURE);
        }
    }
}
