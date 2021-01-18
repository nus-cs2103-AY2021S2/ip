import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // initialise all necessary variables
        Scanner inputScanner = new Scanner(System.in);
        Boolean terminate = false; // to check if the chatbot should be terminated
        String[] inputHistory = new String[100]; // to store history of inputs
        int historyCounter = 0;

        System.out.println("    Good morning comrade, welcome to KGB.\n    What can I do for you?");

        // chatbot continues to wait for user input until the user chooses to terminate the bot
        while (!terminate) {
            String userInput = inputScanner.nextLine();

            if (userInput.equals("list")) {
                for (int i = 0; i < historyCounter; i++) {
                    System.out.println("    " + Integer.toString(i + 1) + ". " + inputHistory[i]);
                }
            } else if (userInput.equals("bye")) {
                System.out.println("    Goodbye comrade. Hope to see you again soon!");
                terminate = true;
            } else {
                inputHistory[historyCounter] = userInput;
                historyCounter++;
                System.out.println("    added: " + userInput);
            }
        }
    }
}