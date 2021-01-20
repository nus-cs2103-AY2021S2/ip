import java.util.Scanner;

public class DukeBot {
    private final Scanner scanner;
    private final Parser parser;
    private final TaskList taskList;

    public DukeBot(final Scanner sc) {
        this.scanner = sc;
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    public void printOut(final String msg) {
        System.out.println(DukeStrings.SEPARATOR);
        System.out.println(msg);
        System.out.println(DukeStrings.SEPARATOR);
    }

    public void run() {
        Command command;
        printOut(DukeStrings.MESSAGE_WELCOME);

        while (!parser.isBye()) {
            command = parser.parseInput(scanner.nextLine());
            printOut(command.execute(taskList));
        }
    }
}
