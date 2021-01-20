import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        CommandMap commands = new CommandMap(new CommandDecorator(new DefaultCommand()));

        ICommand printCommand = new CommandDecorator(new PrintCommand());
        commands.add("done", new CommandDecorator(new DoneCommand(taskManager)));
        commands.add("list", new CommandDecorator(new PrintListCommand(taskManager)));
        commands.add("bye", new CommandDecorator(new ExitCommand(taskManager)));
        commands.add("event", new CommandDecorator(new AddCommand(taskManager,new EventFactory())));
        commands.add("todo", new CommandDecorator(new AddCommand(taskManager,new ToDoFactory())));
        commands.add("deadline", new CommandDecorator(new AddCommand(taskManager,new DeadlineFactory())));

        printCommand.execute(getIntro());
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
