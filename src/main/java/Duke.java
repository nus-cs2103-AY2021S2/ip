import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class for Duke program
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
       DukeBot dukeBot = new DukeBot();

        while (true) {
            String command = sc.nextLine();
            try {
                dukeBot.handleCommand(command);
            } catch(DukeException dukeEx) {
                // Echoes out reason for invalid inputs
                dukeBot.respondToCommand(dukeEx.toString());
            } catch(IOException ioEx) {
                System.out.println(ioEx);
            }

        }
    }
}
