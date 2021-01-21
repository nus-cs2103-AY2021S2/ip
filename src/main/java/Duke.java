import java.util.Scanner;

/**
 * Duke is the controller for the CLI chat-bot DukeBot that handles task tracking.
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Duke {
    public static void main(String[] args) {
        // Greet the user
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        DukeBot dukeBot = new DukeBot(scanner);
        dukeBot.run();
    }
}
