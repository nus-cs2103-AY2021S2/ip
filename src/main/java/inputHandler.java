import java.util.Scanner;

public class inputHandler {
    /**
     * Reads user input and parses it
     * @return Command type object with parameters entered by user
     */
    public static Command parseInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equals("bye")) {
            return new Bye();
        } else {
            return new Echo(userInput);
        }
    }
}

