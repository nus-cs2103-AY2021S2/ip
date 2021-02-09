/**
 * Command class which is a parent class for different types of commands
 */
public abstract class Command {
    protected String line;
    int itemNo = -1;

    /**
     *
     * @param line the input line that the user keyed in
     *  initialises a new command object
     */
    public Command(String line) {
        this.line = line;
    }

    public Command() {

    }

    public abstract boolean isExit();
    public abstract String getType();

    public abstract String getDescription();
}
