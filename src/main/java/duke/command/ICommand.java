package duke;

/**
 * Interface for Command.
 *
 */
interface ICommand {
    /**
     * Execute given action based on the command.
     *
     * @param parameters input required by the Command. See implementations of Command
     *                   for more information.
     */
    void execute(String parameters);
}
