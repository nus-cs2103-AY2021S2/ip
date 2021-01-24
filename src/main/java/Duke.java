import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I help you with today! :-)");

        Scanner scan = new Scanner(System.in);
        Storage storage = new Storage("data/tasks.txt");
        storage.checkFileExistence();
        TaskList tasks = new TaskList(storage.loadTasks());
        Parser parser = new Parser(storage,tasks);


        while (true) {
            String command = scan.nextLine();
            parser.handleCommand(command);
            if (command.equals("bye")) {
                break;
            }
        }
    }
}
