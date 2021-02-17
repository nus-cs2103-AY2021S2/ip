package duke.ui;

import java.util.Scanner;
/**
 * Represents a UI for the duke program. A <code>Ui</code> object corresponds to
 * a UI for the program e.g., <code>terminal</code>
 */
public class Ui {
    /**
     * Returns the user input at the terminal as a String to the program.
     *
     * @param keyboard A scanner that will take the user input.
     * @return message The user input will be transformed into a String named message.
     */
    public String readCommand(Scanner keyboard) {
        String message = new String();
        System.out.println("****************** User Message *****************");
        if (keyboard.hasNextLine()) {
            message = keyboard.nextLine();
        }
        System.out.println("*************************************************");
        return message;
    }

    /**
     * Prints out the welcome message when the program starts.
     */
    public void welcome() {
        display("Hello! I am Duke!\nWhat can I do for you?");
    }

    /**
     * Takes the message from the program and then transfer into the designed UI format.
     *
     * @param botMessage The string that the robot (program) wants to show.
     */
    public void display(String botMessage) {
        System.out.println("**************** Chatbot Message ****************");
        System.out.println(botMessage);
        System.out.println("*************************************************");
    }


}
