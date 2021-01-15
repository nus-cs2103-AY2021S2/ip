import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Output initial greeting before asking for input
        String initialGreeting = "Hello I'm Duke \n"
                + "What can I do for you? \n";
        System.out.println(initialGreeting);

        // Standard Input Scanner
        Scanner sc = new Scanner(System.in);

        // User input data
        String userInput;
        ArrayList<String> userInputs = new ArrayList();

        // Loop through and echo the input until the user inputs "bye"
        while (true) {
            userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // Exit when the user inputs "bye"
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equals("list")) {
                // Shows the list of user inputs with its numbering starting from 1
                for (int index = 0; index < userInputs.size(); index++) {
                    System.out.println(index + 1 + ". " + userInputs.get(index));
                }
            } else {
                // Otherwise, store the user input into an arraylist
                userInputs.add(userInput);
                System.out.println("added: " + userInput);
            }

        }
    }
}
