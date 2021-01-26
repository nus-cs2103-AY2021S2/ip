import java.util.HashMap;
import java.util.Map;

/**
 * Class that maps keywords to appropriate commands.
 */
class CommandMap{
    private Map<String, ICommand> commands;
    private ICommand defaultCommand;

    /**
     * Create and initialise CommandMap. Will execute defaultCommand if keyword
     * given does not match with any keywords that exist in the Map.
     *
     * @param defaultCommand Command that will be executed if keyword given does not match any existing keywords.
     */
    CommandMap(ICommand defaultCommand) {
        this.commands = new HashMap<String, ICommand>();
        this.defaultCommand = defaultCommand;
    }

    /**
     * Maps the given string to the given command
     *
     * @param string Keyword to be mapped.
     * @param command Command to be executed.
     */
    protected void add(String string, ICommand command) {
        commands.put(string,command);
    }

    /**
     * Returns the command that is mapped to keyword. Will return defaultCommand if string does not
     * exist in the Map.
     *
     * @param string Keyword
     * @return Command that corresponded with keyword.
     */
    protected ICommand get(String string) {
        ICommand command = commands.getOrDefault(string,defaultCommand);
        return command;
    }
}
