package classes;

import java.util.Scanner;

/**
 * Ui (User Interface) class to deal with interactions with the user.
 */
public class Ui {
    String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";

    /**
     * Print welcome message when user opens Duckie.
     */
    public void startMessage() {
        System.out.println("yo im Duckie! quack quack");
        System.out.println("what can i do for ya ;)");
        System.out.println(line);
    }

    /**
     * Print end message when user enters 'bye' to stop Duckie.
     */
    public void endMessage() {
        System.out.println(line);
        System.out.println("goodbye! c ya soon ;)");
        System.out.println(line);
    }

    /**
     * Print custom line of smileys as a separator line.
     */
    public void customLine() {
        System.out.println(line);
    }

    /**
     * Method to read the user's input.
     * @return String user input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void findMessage() {
        System.out.println("found it! here's your task(s):");
    }

}
