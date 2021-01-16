import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeBot dukeBot = new DukeBot();

        while(true) {
            String command = sc.nextLine();
            dukeBot.handleCommand(command);
        }
    }
}
