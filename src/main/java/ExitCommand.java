/**
 * A command that represents exiting the program
 */
public class ExitCommand extends Command {

    boolean isExit;

    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * Indicates that the program is at the exit
     *
     * @return a boolean that is true when Exit Command is created
     */
    @Override
    public boolean isExit() {
        return isExit;
    }

}
