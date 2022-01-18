import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        final String greetingMsg = "Hello! I'm DatoDato! Your personal helper bot. :) \n" +
                "What can I do for you?";
        final String leavingMsg = "Bye! Checkout another cool bot  @KatoKatoBot on Telegram.\n " +
                "Hope to see you again soon! \n";

        new ResponePrinter(greetingMsg).print();
    }
}
