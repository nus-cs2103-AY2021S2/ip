import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        final String greetingMsg = "Hello! I'm DatoDato! Your personal helper bot. :) \n" +
                "What can I do for you?";
        final String exitMsg = "Bye! Checkout another cool bot @KatoKatoBot on Telegram.\n" +
                "Hope to see you again soon!";

        // Print Welcome Message
        new ResponePrinter(greetingMsg).print();

        Scanner sc = new Scanner(System.in);
        String inputTxt;

        while(true) {
            inputTxt = sc.nextLine();

            if (!inputTxt.equals(Command.EXIT.getCommand())) {
                new ResponePrinter(inputTxt).print();
            } else {
                break;
            }
        }

        // Print Exiting Message
        new ResponePrinter(exitMsg).print();
    }
}
