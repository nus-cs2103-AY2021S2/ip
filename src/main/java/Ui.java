import java.util.Scanner;

/**
 * Ui handles interaction with user.
 */
public class Ui {

    /** Scanner to take in input from user. */
    private Scanner sc;

    /**
     * Initializes a newly created Ui object.
     * Prints out a welcome message from Duke.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Waits for and takes in a new input from user.
     * Prints out message to prompt user to enter input.
     *
     * @return String representing the input given by user.
     */
    protected String nextInput() {
        System.out.println("Enter an input ('bye' to quit): ");
        String userInput = sc.nextLine();
        System.out.println("User Input: " + userInput);
        return userInput;
    }

    /**
     * Closes the application.
     */
    protected void close() {
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

}
