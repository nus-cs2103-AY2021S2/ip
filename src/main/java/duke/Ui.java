package duke;

import java.util.Scanner;

/**
 * Handles the User interface interactions with the user. Provides various methods to read in the command
 * and print out things to display.
 */
public class Ui {
    /** logo for welcome message. */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER =
            "\n___________________________________________________________________________\n";

    private Scanner scForCommandLine= new Scanner(System.in);

    /**
     * displays the welcome message when first starting duke.
     */
    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * gets a line of user input from Command line, trimmed.
     * @return String containing a line input from user.
     */
    public String getUserCommand(){
        String input;
        input = scForCommandLine.nextLine().trim();
        return input;
    }



    /**
     * Prints all the tasks stored on the TaskList.
     */

    public void showUserAllTasks(TaskList listOfTasks){
        int counter = 1;
        for (Task currentTask : listOfTasks) {
            System.out.println(counter + "." + currentTask);
            counter++;
        }
    }

}

