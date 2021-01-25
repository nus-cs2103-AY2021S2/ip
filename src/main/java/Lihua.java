import lihua.commands.Command;
import lihua.commands.CommandResult;
import lihua.commands.ExitCommand;
import lihua.parser.Parser;
import lihua.storage.Storage;
import lihua.tasks.Tasks;
import lihua.ui.Ui;

// Design idea adapted from https://github.com/se-edu/addressbook-level2
public class Lihua {
    private Tasks tasks;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        new Lihua().run(args);
    }

    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseUserInput(userInput);
            CommandResult result = executeCommand(command);
            ui.showFeedbackToUser(result);
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            CommandResult result = command.execute();
            storage.saveTasks(tasks);
            return result;
        } catch (Exception e) {
            ui.showFeedbackToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void start(String[] args) {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = storage.load();
            ui.printHello();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        System.exit(0);
    }
}
