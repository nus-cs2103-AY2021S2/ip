import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        CommandMap commands = new CommandMap(new CommandDecorator(new DefaultCommand()));
        Storage storage= new Storage(taskManager);

        ICommand printCommand = new CommandDecorator(new PrintCommand());

        ICommand doneCommand = new CommandDecorator(new DoneCommand(taskManager));
        doneCommand = new CommandWrite(doneCommand,storage);

        ICommand listCommand =new CommandDecorator(new PrintListCommand(taskManager));

        ICommand exitCommand =new CommandDecorator(new ExitCommand(taskManager));

        ICommand eventCommand = new CommandDecorator(new AddCommand(taskManager,new EventFactory()));
        eventCommand = new CommandWrite(eventCommand,storage);

        ICommand deadlineCommand =new CommandDecorator(new AddCommand(taskManager,new DeadlineFactory()));
        deadlineCommand = new CommandWrite(deadlineCommand,storage);

        ICommand toDoCommand = new CommandDecorator(new AddCommand(taskManager,new ToDoFactory()));
        toDoCommand = new CommandWrite(toDoCommand,storage);

        ICommand deleteCommand = new CommandDecorator(new DeleteCommand(taskManager));
        deleteCommand = new CommandWrite(deleteCommand,storage);

        commands.add("done", doneCommand);
        commands.add("list", listCommand);
        commands.add("bye", exitCommand);
        commands.add("event", eventCommand);
        commands.add("todo", toDoCommand);
        commands.add("deadline", deadlineCommand);
        commands.add("delete", deleteCommand);
        printCommand.execute(getIntro());
        storage.read();

        try {
            while (!taskManager.hasExited()) {
                String input = scanner.nextLine();
                String[] inputArray = input.split(" ", 2);
                if (inputArray.length == 2) {
                    commands.get(inputArray[0]).execute(inputArray[1]);
                } else if (inputArray.length == 1) {
                    //for commands with only one word, will give error msg if command requires more than 1.
                    commands.get(inputArray[0]).execute(" ");
                }
            }
        } catch (NoSuchElementException e) {
            printCommand.execute("Error. No more lines detected. Exiting...");
        }
        finally {
            scanner.close();
        }
    }

    private static String getIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro ="Hello I'm\n" + logo +"\nWhat can I do for you?\n";

        return intro;
    }
}
