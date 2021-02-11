package duke.user;
import java.util.Scanner;
/**
 * Represents a user for the duke program. A <code>user</code> object corresponds to
 * a user for the program e.g., <code>Student 1</code>
 */
public class User {
    /**
     * A constructor for User class
     *
     * @param keyboard The scanner that will be used to get user input.
     */
    public String inputMessage(Scanner keyboard) {
        String message = new String();
        System.out.println("****************** User Message *****************");
        if (keyboard.hasNextLine()) {
            message = keyboard.nextLine();
        }
        System.out.println("*************************************************");
        return message;
    }
}

