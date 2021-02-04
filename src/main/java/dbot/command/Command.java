package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DBotException;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;

import javax.swing.*;

/**
 * Command is an abstract class that is to be extended to represent any valid user commands supplied to DBot.
 */
public abstract class Command {

    /** A String containing the description that this Command's Task should have. */
    protected String description;

    /** A flag representing whether this Command's Task should be marked as done. */
    private boolean isDone;

    /** The index of the Task that this command targets in the TaskList. */
    private int targetIndex = -1;

    /**
     * Initialize a command with its corresponding Task to be marked as not done.
     */
    public Command() {
        isDone = false;
    }

    /**
     * Initialize a command with its corresponding Task to be marked as not done and to have the supplied
     * description String.
     *
     * @param description A String containing the description that the Task corresponding to this Command
     *                    should have.
     */
    public Command(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Initializes a Command with the specified target index for a 0-indexed TaskList.
     *
     * @param targetIndex An integer representing the target index of a 0-indexed TaskList.
     */
    public Command(int targetIndex) {
        setTargetIndex(targetIndex);
    }

    /**
     * Sets the target index instance variable.
     *
     * @param targetIndex A integer specifying the new target index for the 0-indexed TaskList.
     */
    protected void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Returns the target index for the 0-indexed TaskList.
     *
     * @return An integer specifying the target index for the 0-indexed TaskList.
     */
    protected int getTargetIndex() {
        return targetIndex;
    }

    /**
     * Returns the description that was supplied to this Command.
     *
     * @return A String containing the description that was supplied to this Command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if this Command is an Exit Command and false otherwise.
     *
     * @return True if this is an Exit Command and false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if the Task corresponding to this Command is done and false otherwise.
     *
     * @return True if this Command's corresponding Task is done and false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets a boolean flag representing whether this Command's corresponding Task is done.
     *
     * @param isDone True if this Command's corresponding Task is done and false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Executes this Command appropriately and provides any relevant response messages to the user.
     *
     * @param tasks A TaskList that this Command should be executed on.
     * @param ui A Ui object that this Task should use to send response messages to the user.
     * @param storage A Storage object that this Command should use to save the TaskList to if appropriate.
     * @throws DBotException If the Command cannot be executed because it encounters any errors.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DBotException;

    /**
     * Executes this Command appropriately without providing any response messages to the user.
     *
     * @param tasks A TaskList that this Command should be executed on.
     * @param storage A Storage object that this Command should use to save the TaskList to if appropriate.
     * @throws DBotException If the Command cannot be executed because it encounters any errors.
     */
    public abstract void quietExecute(TaskList tasks, Storage storage) throws DBotException;

    /**
     * Returns true if the comparison object is an equivalent Command object.
     *
     * For a Command to be equivalent it must satisfy all three of the following:
     *   - Be of the same concrete class
     *   - Have the same description
     *   - Have the same boolean flag for whether its corresponding Task is done
     *
     * @param o The comparison Object.
     * @return True if the comparison object is an equivalent Command and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Command) {
            Command other = (Command) o;
            boolean isSameClass = this.getClass() == other.getClass();
            boolean hasSameDescription = this.getDescription().equals(other.getDescription());
            boolean isSameDone = this.getIsDone() == other.getIsDone();
            return isSameClass && hasSameDescription && isSameDone;
        } else {
            return false;
        }
    }
}
