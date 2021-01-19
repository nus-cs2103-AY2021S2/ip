import java.util.Scanner;

public class InputHandler {
    /**
     * Reads user input and parses it
     * @return Command type object with parameters entered by user
     */
    public static Command parseInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.toLowerCase().equals("bye")) {
            return new ByeCommand();
        } else if (userInput.toLowerCase().equals("list")) {
            return new ListCommand();
        } else {
            return new AddCommand(userInput);
        }
    }
}

