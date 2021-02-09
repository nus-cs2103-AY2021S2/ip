/**
 * a deadline command object
 */
public class DeadlineCommand extends Command {
    /**
     *
     * @param line the description of the deadline command
     */
    DeadlineCommand(String line) {
        super(line);
    }

    /**
     *
     * @return its not bye so it returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return deadline
     */
    @Override
    public String getType() {
        return "deadline";
    }
    /**
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return line;
    }
}
