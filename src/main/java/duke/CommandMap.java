package duke;

import java.util.HashMap;
import java.util.Map;

import duke.command.ICommand;

/**
 * Class that maps keywords to appropriate commands.
 */
public class CommandMap {
    private Map<String, ICommand> commands;
    private ICommand defaultCommand;

    /**
     * Create and initialise duke.CommandMap. Will execute defaultCommand if keyword
     * given does not match with any keywords that exist in the Map.
     *
     * @param defaultCommand Command that will be executed if keyword given does not match any existing keywords.
     */
    public CommandMap(ICommand defaultCommand) {
        this.commands = new HashMap<String, ICommand>();
        this.defaultCommand = defaultCommand;
    }

    /**
     * Maps the given string to the given command
     *
     * @param string Keyword to be mapped.
     * @param command Command to be executed.
     */
    public void add(String string, ICommand command) {
        commands.put(string, command);
    }

    /**
     * Returns the command that is mapped to keyword. Will return defaultCommand if string does not
     * exist in the Map.
     *
     * @param string Keyword
     * @return Command that corresponded with keyword.
     */
    public ICommand get(String string) {
        ICommand command = commands.getOrDefault(string, defaultCommand);
        return command;
    }
}
