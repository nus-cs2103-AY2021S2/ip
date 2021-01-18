import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // initialise all necessary variables
        Scanner inputScanner = new Scanner(System.in);
        Boolean terminate = false; // to check if the chatbot should be terminated

        System.out.println("    Good morning comrade, welcome to KGB.\n    What can I do for you?");

        // chatbot continues to wait for user input until the user chooses to terminate the bot
        while (!terminate) {
            String userInput = inputScanner.nextLine();

            if (userInput.equals("list")) {
                System.out.println("    Here you have your requested name list of the traitors of our motherland.");
            } else if (userInput.equals("operation")) {
                System.out.println("    Here you have the operation plan to sabotage the CIA");
            } else if (userInput.equals("bye")) {
                System.out.println("    Goodbye comrade. Hope to see you again soon!");
                terminate = true;
            } else {
                System.out.println("    I don't understand what you are saying.");
            }
        }

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}