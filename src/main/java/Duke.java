import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeBot dukeBot = new DukeBot();

        while (true) {
            String command = sc.nextLine();
            try {
                dukeBot.handleCommand(command);
            } catch(DukeException ex) {
                dukeBot.respondToCommand(ex.getMessage());
            }
        }
    }
}
