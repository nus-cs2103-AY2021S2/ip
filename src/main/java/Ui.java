import java.io.IOException;

/**
 * A class which contains list of user interfaces.
 */
public class Ui {
    /**
     * Say bye when the user logouts.
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred. This class is the
     *                     general class of exceptions produced by failed or interrupted I/O operations.
     */
    public void bye() throws IOException {
        Duke.respond = "Bye. Hope to see you again soon!\nYour data is saved!\n" +
                "Click [X] to exit the program!";
        System.out.println("Program terminated!");
        Storage.save();
    }

    /**
     * Welcome the user when login.
     */
    public void greet() {
        Duke.respond = "Hello there, I am Duke :P+" + "\n" + "How can I help you?";
    }
}