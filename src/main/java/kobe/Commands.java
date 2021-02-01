package kobe;

import java.lang.System;

public class Commands {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;

    /**
     * Saves the task list, and ends the program.
     *
     * @param storage  the storage object associated to the location of the
     *                 text file where the task list will be saved at
     */
    public static void goodbye(Storage storage) {
        storage.saveFile();
        System.out.println(line + "Bye. Kobe saved your list.\n" + ind
                + "Kobe hopes to see you again soon!\n" + line);
        System.exit(0);
    }

    /**
     * Shows the current list to the user.
     *
     * @param tasks  the TaskList object that stores the current task list
     * @param ui  the user interface to inform the user of the outcome
     */
    public static void showList(TaskList tasks, Ui ui) {
        System.out.print(line + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(ind + (i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        ui.showLine();
    }


}