import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Init bot
        Bot bot = new Bot("SimpleBot");
        System.out.println(bot.getInitMessage());
        
        // Perform main logic loop
        try (Scanner scanner = new Scanner(System.in)) {
            while (bot.isActive()) {
                System.out.print("You: ");
                String reply = bot.respond(scanner.nextLine());
                // Additional line break
                System.out.println();
                System.out.println(reply);
            }
        }
    }
}
