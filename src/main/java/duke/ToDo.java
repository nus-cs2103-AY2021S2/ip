package duke;

/** Represents ToDo inherited from Command,
 * attached with command description
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */

public class ToDo extends Command {

    /**
     * Constructor for Command child class ToDo
     *
     * @param commandDescription every command has a description attached
     */

    public ToDo(String commandDescription) {
        super(commandDescription);
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
