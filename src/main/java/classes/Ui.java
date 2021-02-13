package classes;

import java.util.Scanner;

/**
 * Ui (User Interface) class to deal with interactions with the user.
 */
public class Ui {
    private String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";

    /**
     * Print welcome message when user opens Duckie.
     * @return
     */
    public String startMessage() {
        return "yo im Duckie! quack quack! what can i do for ya ;)" + "\n" + line;
    }

    /**
     * Print end message when user enters 'bye' to stop Duckie.
     */
    public String endMessage() {
        return "goodbye! c ya soon ;)" + "\n" + line;
    }

    /**
     * Print custom line of smileys as a separator line.
     * @return
     */
    public String customLine() {
        return line;
    }

    /**
     * Method to read the user's input.
     * @return String user input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String showErrorMessage(Exception e) {
        return e.getMessage();
    }

    public String findMessage() {
        return "found it! here's your task(s):";
    }

}
