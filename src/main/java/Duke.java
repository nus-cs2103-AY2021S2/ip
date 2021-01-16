import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Start up greeting message
        String greetingMessage = "Hello! I'm a Chat bot and my name " +
                "is Joe \nHow may I help you?";
        System.out.println(messageFormatter(greetingMessage));

        boolean isChatBotOnline = true;
        while (isChatBotOnline) {
            // Listen to input
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            // Echoing the input
            if (input.equals("bye")) {
                String byeMessage = "Goodbye, hope you had a great time!";
                System.out.println(messageFormatter(byeMessage));
                isChatBotOnline = false;
            } else {
                System.out.println(messageFormatter(input));
            }
        }
    }


    public static String messageFormatter(String str) {
        return "____________________________________________________________" +
                "\n" + str + "\n" +
                "____________________________________________________________\n";
    }
}
