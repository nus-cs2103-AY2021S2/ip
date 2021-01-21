import java.util.Scanner;
import java.util.LinkedList;

/**
 * Duke is a CLI chat-bot that handles task tracking.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class DukeBot {
    private Scanner scanner;
    private LinkedList<String> list;
    private static final String UNDERLINES = "____________________________________________________________";

    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.list = new LinkedList<String>();
    }

    // Static methods
    public static String getCommand(String input) {
        return String.valueOf(input).split("\\\\s", 2)[0];
    }


    // Instance methods
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            boolean endFlag = this.processInput(input);
            if (endFlag) {
                System.out.println(UNDERLINES);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(UNDERLINES);
                break;
            }
        }
        this.scanner.close();
    }

    /**
     * Parses user input and returns a boolean flag indicating whether to terminate.
     *
     * @param input The user input string
     * @return true if the programme should end and false otherwise
     */
    public boolean processInput(String input) {
        String first = DukeBot.getCommand(input).toLowerCase();
        Command command = Command.get(first);
        if (command == null) {
            this.add(input);
        } else {
            switch (command) {
                case LIST:
                    this.printList();
                    break;
                case END:
                    return true;
            }
        }
        return false;
    }

    public void add(String input) {
        this.list.add(input);
        System.out.println(UNDERLINES);
        System.out.println("added: " + input);
        System.out.println(UNDERLINES);
    }

    public void printList() {
        System.out.println(UNDERLINES);
        int count = 1;
        for (String task : this.list) {
            System.out.println(" " + count + ": " + task);
            count++;
        }
        System.out.println(UNDERLINES);
    }
}
