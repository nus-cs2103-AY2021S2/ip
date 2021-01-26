import java.util.Scanner;

import weiliang.bot.Bot;
import weiliang.bot.BotException;

public class Duke {
    public static void main(String[] args) {
        // Init bot
        Bot bot = new Bot("SimpleBot", "memory.txt");
        System.out.println(bot.getInitMessage());
        
        // Perform main logic loop
        try (Scanner scanner = new Scanner(System.in)) {
            while (bot.isActive()) {
                System.out.print("You: ");
                String reply;
                try {
                    reply = bot.respond(scanner.nextLine());
                } catch (BotException e) {
                    reply = bot.formatMessage(e.getMessage());
                }
                // Additional line break
                System.out.println();
                System.out.println(reply);
            }
        }
    }
}
