package duke.register;
import java.util.Scanner;

/**
 * This class is mainly for the input
 */
public class Ui {
    Scanner ui;

    public Ui() {
        ui = new Scanner(System.in);
    }

    /**
     * Gets the command from the user
     * @return
     */
    public String getCommand() {
        return ui.nextLine();
    }

    public void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello this is Duke\nHow may I help you?");
    }

    public void goodbye() {
        System.out.println("Goodbye for now.\nHope to see you soon!");
    }
}
