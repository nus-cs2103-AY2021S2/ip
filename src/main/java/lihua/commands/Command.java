package lihua.commands;

import lihua.tasks.Tasks;

/**
 * Parent class for all commands. This class should be abstract since
 * it is meaningless to initialize instances of 'Command' only.
 */
public abstract class Command {
    /** The tasks list to operate on */
    protected Tasks tasks;
    /** The target index of the task list, if applicable */
    protected int targetIndex = -1;

    /**
     * Initializes a Command Object with a target index.
     *
     * @param targetIndex The 1-based index of the task to be operated on.
     */
    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Initializes a default Command Object.
     */
    public Command() {
    }

    /**
     * Set a task list to the command object.
     *
     * @param tasks The tasks list to operate on later.
     */
    public void setTaskList(Tasks tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the command. To be implemented by children classes.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    public abstract CommandResult execute();
}
