import java.util.Scanner;

/**
 * Class that can create a user interface object that
 * can deal with inteactions between the user and the
 * application.
 */
public class UI {
    private Parser parser;

    /**
     * Constructor that creates a UI object.
     */
    public UI() {
        parser = new Parser();
    }

    /**
     * Method that launches the UI for the user.
     *
     * @param listOfTasks list of tasks available upon launching
     *                    the UI.
     */
    public void launchUI(TaskList listOfTasks) {
        Scanner read = new Scanner(System.in);

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you");
        System.out.println("\t_____________________________________________________________");

        String textInput = read.nextLine();
        Boolean checkBye = false;
        if (textInput.toLowerCase().equals("bye")) {
            checkBye = true;
        }

        while (!checkBye) {
            parser.executeCommand(listOfTasks, textInput);
            textInput = read.nextLine();
            if (textInput.toLowerCase().equals("bye")) {
                checkBye = true;
            }
        }

        System.out.println("\t_____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t_____________________________________________________________");
    }
}
