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

    /**
     * Returns the first word present in a given line of input text.
     *
     * @param input The user input text of String type
     * @return A lowercase String
     */
    public static String getFirstWord(String input) {
        return String.valueOf(input).split("\\\\s", 2)[0].toLowerCase();
    }


    // Instance methods

    /**
     * Activates the chat-bot so that it keeps taking inputs from the user via System.in
     * until the "bye" input is given.
     */
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
        String first = DukeBot.getFirstWord(input);
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

    /**
     * Adds a new item to the chat-bots list.
     *
     * @param input A String of text that should be added as an entry to the list
     */
    public void add(String input) {
        this.list.add(input);
        System.out.println(UNDERLINES);
        System.out.println("added: " + input);
        System.out.println(UNDERLINES);
    }

    /**
     * Prints all the existing list items that this current chat-bot was made to track.
     */
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

