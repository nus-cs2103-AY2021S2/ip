import java.util.HashMap;
import java.util.Map;

class CommandMap{
    private Map<String, ICommand> commands;
    private ICommand defaultCommand;

    CommandMap(ICommand defaultCommand) {
        this.commands = new HashMap<String, ICommand>();
        this.defaultCommand = defaultCommand;
    }

    protected void add(String string, ICommand command) {
        commands.put(string,command);
    }

    protected ICommand get(String string) {
        ICommand command = commands.getOrDefault(string,defaultCommand);
        return command;
    }
}
