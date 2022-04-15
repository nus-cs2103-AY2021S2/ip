package duke.command;

/**
 * Interface for Command.
 *
 */
public interface ICommand {
    /**
     * Execute given action based on the command.
     *
     * @param parameters input required by the Command. See implementations of Command
     *                   for more information.
     */
    public void execute(String parameters);
}
