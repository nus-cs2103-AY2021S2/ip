/**
 * base abstract class that all commands inherit from.
 */

public abstract class Command {
    public abstract Result execute() throws DukeException, InterruptedException;
}
