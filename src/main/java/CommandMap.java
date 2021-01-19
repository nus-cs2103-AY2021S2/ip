import java.util.HashMap;
import java.util.Map;

public class CommandMap{
    private Map<String, ICommand> commands;
    private ICommand defaultCommand;

    public CommandMap(ICommand defaultCommand) {
        this.commands = new HashMap<String, ICommand>();
        this.defaultCommand = defaultCommand;
    }

    public void add(String string, ICommand command) {
        commands.put(string,command);
    }

    public ICommand get(String string) {
        ICommand command = commands.getOrDefault(string,defaultCommand);
        return command;
    }


}
