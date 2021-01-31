package duke.driver;

import java.util.Scanner;

import duke.command.Command;
import duke.ui.Ui;
import duke.utils.Parser;
import duke.utils.TaskStorage;


/**
 * Driver class of Duke that handles the command key in by user and respond.
 */
public class DukeDriver {

    /**
     * Take in user input and execute the Duke program.
     */
    public static void executeDuke() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Ui.LOGO);
        System.out.println(Ui.GREETING);

        Ui.sleep();

        TaskStorage.loadFiles();

        while (sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Ui.FAREWELL);
    }


    /**
     * The main handler of all the command.
     *
     * @param message String representation of the message key in by user.
     * @return a boolean to whether to exit the program.
     */
    private static boolean inputHandler(String message) {
        Command command = new Parser(message).parse();
        return command.execute();
    }


}
