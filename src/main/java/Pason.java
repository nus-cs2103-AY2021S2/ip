import pason.commands.Command;
import pason.commands.CommandResult;
import pason.commands.CommandResultType;
import pason.exceptions.PasonException;
import pason.parser.Parser;
import pason.storage.Storage;
import pason.tasks.TaskList;

public class Pason {
    private TaskList tasks;
    private final Storage storage;

    /**
     * Main Pason method.
     */
    public Pason() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage);
        } catch (PasonException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public CommandResult getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, storage);
        } catch (Exception e) {
            return new CommandResult(e.getMessage(), CommandResultType.ERROR);
        }
    }

    public static void main(String[] args) {

    }
}
