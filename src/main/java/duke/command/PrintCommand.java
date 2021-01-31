package duke.command;

/**
 * Command to print given string.
 */
public class PrintCommand implements ICommand {
    /**
     * Execute the printing of given input.
     *
     * @param parameters Given input to be printed
     */
    @Override
    public void execute(String parameters) {
        System.out.println(parameters);
    }
}
