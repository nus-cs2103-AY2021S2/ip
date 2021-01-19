import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        CommandMap commands = new CommandMap(new CommandDecorator(new AddCommand(taskManager)));
        ICommand printCommand = new CommandDecorator(new PrintCommand());
        commands.add("done", new CommandDecorator(new DoneCommand(taskManager)));
        commands.add("list", new CommandDecorator(new PrintListCommand(taskManager)));
        commands.add("bye", new CommandDecorator(new ExitCommand(taskManager)));

        printCommand.execute(getIntro());
        while (!taskManager.hasExited()) {
            String input = scanner.nextLine();
            input = input.replace("\n", "");
            String[] parsedInput = input.split(" ");
            commands.get(parsedInput[0]).execute(parsedInput);
        }
    }

    private static String[] getIntro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro ="Hello I'm\n" + logo +"\nWhat can I do for you?\n";

        String[] introArray = new String[]{intro};
        return introArray;
    }
}
