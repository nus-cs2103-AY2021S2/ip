package duke;

import java.util.Scanner;

/**
 * Handles the User interface interactions with the user. Provides various methods to read in the command
 * and print out things to display.
 */
public class Ui {

    /*logo for welcome message.*/
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER =
            "\n_______________________________________________________\n";

    private Scanner scForCommandLine = new Scanner(System.in);

    private String messageToDisplay = "";

    /**
     * Returns the welcome message when first starting duke.
     */
    public String displayWelcomeMessage() {
        return "Hello from\n" + LOGO + "Hello! I'm Duke\nWhat can I do for you?";
    }


    /**
     * gets a line of user input from Command line, trimmed.
     *
     * @return String containing a line input from user.
     */
    public String getUserCommand() {
        String input;
        do {
            input = scForCommandLine.nextLine().trim();
        } while (shouldIgnore(input));
        return input;
    }

    /**
     * Ignores input if string does not contain any non-space characters. ( Could have multiple spaces,tabs etc)
     *
     * @return true a boolean value to indicate if the string is to be ignored.
     */
    public boolean shouldIgnore(String input) {
        if (input.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns the string of all the tasks stored on the TaskList.
     */

    public static String getDisplayOfAllTasks(TaskList listOfTasks) {
        String output = "";
        int counter = 1;
        for (Task currentTask : listOfTasks) {
            output += (counter + "." + currentTask + "\n");
            counter++;
        }
        return output;
    }

    public static String getDisplayOfNumberOfTasks(TaskList listOfTasks) {
        return "Now you have " + listOfTasks.size() + " tasks in the list.";
    }

    public void setDisplayMessage(String messageToDisplay){
        this.messageToDisplay = messageToDisplay;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }



}

