package duke.command;

/**
 * Default Command which will get executed when keyword supplied does not match with keywords in CommandMap.
 */
public class DefaultCommand implements ICommand {
    /**
     * When executed, will print a default statement. Input given will not change
     * how function gets executed.
     *
     * @param parameters
     */
    @Override
    public void execute(String parameters) {
        System.out.println("Error: Unknown command word. Please try again.");
    }
}
