import data.Duke;
import data.IDuke;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IDuke bot = Duke.getDuke("data/duke.txt");
        Scanner sc = new Scanner(System.in);

        // Greet user
        bot.greeting();

        // Handle commands until user types bye
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (!s.matches("\\s*")) {
                // Ignore white spaces or empty lines
                bot = bot.processInput(s);
            }
        }

        // Exit bot
        bot.bye();
    }
}