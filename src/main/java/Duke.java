import java.util.Scanner;

/**
 * Driver class for Duke project
 */
public class Duke {
    public static void main(String[] args) {
        DukeBot dukeBot = new DukeBot();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while(dukeBot.echo(userInput)) {
            userInput = sc.nextLine();
        }
    }
}