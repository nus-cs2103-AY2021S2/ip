package duke;

import java.util.Scanner;

public class Ui {
    /**
     * Handles the User interface interactions with the user. Trims the command,
     * prints out something to display.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER =
            "\n_______________________________________________________\n";

    private Scanner scForCommandLine= new Scanner(System.in);

    public void displayWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

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

