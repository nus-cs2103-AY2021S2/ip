/**
 * to do command class
 */
public class ToDoCommand extends Command {
    /**
     *
     * @param line the description of the task to do
     */
    public ToDoCommand(String line) {
        super(line);
    }

    /**
     *
     * @return false since its not terminating
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     *
     * @return todo
     */
    @Override
    public String getType() {
        return "todo";
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
