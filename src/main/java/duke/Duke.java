import java.util.NoSuchElementException;

public class Duke {
    private Storage storage;
    private CommandMap commands;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.commands = new CommandMap(new CommandBorder(new DefaultCommand()));
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke currentSession = new Duke();
        currentSession.initialiseCommandMap();
        currentSession.run();
    }

    private void run() {
        ICommand printCommand = new CommandBorder(new PrintCommand());
        printCommand.execute(ui.getIntro());
        try {
            this.storage.read();
            while (!taskList.hasExited()) {
                String input = ui.getLine();
                String[] inputArray = Parser.parse(input);
                if (inputArray.length == 2) {
                    commands.get(inputArray[0]).execute(inputArray[1]);
                } else if (inputArray.length == 1) {
                    //for commands with only one word, will give error msg if command requires more than 1.
                    commands.get(inputArray[0]).execute(" ");
                }
            }
        } catch (NoSuchElementException e) {
            printCommand.execute(ui.showNoMoreLinesError());
        }

    }

    private void initialiseCommandMap() {

        ICommand doneCommand = new CommandBorder(new DoneCommand(taskList));
        doneCommand = new CommandWrite(doneCommand,storage);

        ICommand listCommand =new CommandBorder(new PrintListCommand(taskList));

        ICommand exitCommand =new CommandBorder(new ExitCommand(taskList));

        ICommand eventCommand = new CommandBorder(new AddCommand(taskList,new EventFactory()));
        eventCommand = new CommandWrite(eventCommand,storage);

        ICommand deadlineCommand =new CommandBorder(new AddCommand(taskList,new DeadlineFactory()));
        deadlineCommand = new CommandWrite(deadlineCommand,storage);

        ICommand toDoCommand = new CommandBorder(new AddCommand(taskList,new ToDoFactory()));
        toDoCommand = new CommandWrite(toDoCommand,storage);

        ICommand deleteCommand = new CommandBorder(new DeleteCommand(taskList));
        deleteCommand = new CommandWrite(deleteCommand,storage);

        ICommand findCommand = new CommandBorder(new FindCommand(taskList));

        commands.add("done", doneCommand);
        commands.add("list", listCommand);
        commands.add("bye", exitCommand);
        commands.add("event", eventCommand);
        commands.add("todo", toDoCommand);
        commands.add("deadline", deadlineCommand);
        commands.add("delete", deleteCommand);
        commands.add("find", findCommand);
    }
}
