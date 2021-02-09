/**
 * event command class
 */
public class EventCommand extends Command {
    /**
     *
     * @param line the description of the event
     */
    EventCommand(String line) {
        super(line);
    }

    /**
     *
     * @return false since its not bye
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return event
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     *
     * @return event description
     */
    @Override
    public String getDescription() {
        return line;
    }
}
