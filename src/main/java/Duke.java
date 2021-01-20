import java.util.Scanner;

/**
 * Driver class for Duke program
 */
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeBot dukeBot = new DukeBot();

        while (true) {
            String command = sc.nextLine();
            try {
                dukeBot.handleCommand(command);
            } catch(DukeException ex) {
                // Echoes out reason for invalid inputs
                dukeBot.respondToCommand(ex.toString());
            }
        }
    }
}
