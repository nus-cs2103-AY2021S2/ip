package kobe;

import java.lang.System;


public class Commands {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;

    /**
     * Saves the task list, and ends the program.
     *
     * @param storage  the storage object associated to the location of the
     */
    public static void goodbye(Storage storage) {
        Ui.addGoodbyeResponse();
        Storage.saveFile(storage);
        System.out.println(line + "Bye. Kobe saved your list.\n" + ind
                + "Kobe hopes to see you again soon!\n" + line);
//      System.exit(0);
    }

    /**
     * Shows the current list to the user.
     *
     * @param tasks  the TaskList object that stores the current task list
     */
    public static void showList(TaskList tasks) {
        Ui.addShowTaskListResponse(tasks);
    }

    /**
     * Shows the list of commands to the user
     */
    public static void getHelp() {
        Ui.addHelpResponse();
    }

}