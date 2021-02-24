package duke;

/** Represents Command that Doge can do, with a description
 * attached and primary behaviour to mark command as done
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */

public class Command {
    String commandDescription;
    boolean isDone;

    /**
     * Constructor for Command
     *
     * @param commandDescription every command has a description attached
     */
    public Command(String commandDescription) {
        this.commandDescription = commandDescription;
        this.isDone = false;
    }

    /**
     * Mark command as Done
     *
     * @return Command that is marked as Done
     */
    public Command markDone() {
        this.isDone = true;
        return this;
    }

    public String getDescription() {
        return this.commandDescription;
    }

    /**
     * Check if command is marked as done, Command is
     * done when indicated with 1 and vice versa 0
     *
     * @return String to indicate Done attribute
     */
    public String getDone() {
        if (isDone) {
            return " 1 ";
        } else {
            return " 0 ";
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.commandDescription;
        } else {
            return "[ ] " + this.commandDescription;
        }
    }

}