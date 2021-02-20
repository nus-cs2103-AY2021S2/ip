package ui;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Class <code>Ui</code> contains all the string messages that interacts with users and methods
 * <code>printBox</code> and <code>printList</code>.
 */
public class Ui {
    public final static String MARK_MSG = "You have marked this task as completed: \n";
    public final static String ADD_MSG = "Roger that! Added the following task: \n \n      ";
    public final static String DELETE_MSG = "Roger that! Deleted the follow task: \n \n      ";
    public final static String INDENT_4_SPACES = "     ";
    public final static String TOP_BORDER = "\n**********************************************************************\n";
    public final static String BOTTOM_BORDER = "**********************************************************************\n";
    public final static String INDENT_32_SPACES = "                                        ";


    public final static String WELCOME_MSG = INDENT_32_SPACES + "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n"
            + INDENT_32_SPACES + "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
            + INDENT_32_SPACES + "█░░║║║╠─║─║─║║║║║╠─░░█ \n"
            + INDENT_32_SPACES + "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
            + INDENT_32_SPACES + "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
    public final static String LOGO = INDENT_32_SPACES + "──────▄▀▄─────▄▀▄ \n"
            + INDENT_32_SPACES + "─────▄█░░▀▀▀▀▀░░█▄ \n"
            + INDENT_32_SPACES + "─▄▄──█░░░░░░░░░░░█──▄▄ \n"
            + INDENT_32_SPACES + "█▄▄█─█░░▀░░┬░░▀░░█─█▄▄█ ";
    public final static String GREETING_MSG = "Hey there! I'm Kawaii Kat \n"
            + "How can i assist you ~nya~?";
    public final static String EXIT_MSG = "Farewell. See you soon :)!";
    public final static String SAVE_EXIT_MSG = "Task List Saved Successfully!\n"
            + EXIT_MSG;
    public final static String EMPTY_LIST_MSG = "OOPPS! Your Current Task List Is Empty!";
    public final static String TASK_CLASH_MSG = "OOPPS! Sorry :( \n"
            + "Task added clashes with another task in list!";

    /**
     * Surrounds the specified <code>input</code> with a asterisk border.
     *
     * @param input input to be bordered
     */
    public static void printBox(String input) {
        System.out.println(TOP_BORDER + INDENT_4_SPACES + input
                + "\n" + BOTTOM_BORDER);
    }

    /**
     * Prints the Ui of loading save record.
     */
    public static String retrieveList(boolean bool) {
        if (bool) {
            return Ui.formatBox(Ui.INDENT_32_SPACES + "No Save Record Detected... \n"
                    + Ui.INDENT_32_SPACES + "     Creating New List! :)");
        } else {
            return Ui.formatBox(Ui.INDENT_32_SPACES + "Saved Record Detected... \n"
                    + Ui.INDENT_32_SPACES + "     Retrieving List! :)");
        }
    }

    /**
     * Formats the <code>String</code> input into a asterisk box.
     * @param input string that requires formatting
     * @return formatted string
     */
    public static String formatBox(String input) {
        return TOP_BORDER + INDENT_4_SPACES + input
                + "\n" + BOTTOM_BORDER;
    }

    /**
     * Prints the specified <code>TaskList</code> with a format
     * @param list list that requires formatting
     * @return returns a String of formatted list of elements
     */
    public static String printList(TaskList list) {
        if (list.size() == 0 ) {
            return EMPTY_LIST_MSG;
        } else {
            StringBuilder result = new StringBuilder("     This is your present task list: \n");
            for (int i = 1; i <= list.size(); i++) {
                result.append(INDENT_4_SPACES).append(i).append(") ").append(list.get(i - 1)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Surrounds the elements the specified <code>list/code> of search result with a asterisk border.
     *
     * @param list searched elements to be border
     */
    public static String printSearch(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks found: \n");
        for (int i = 1; i <= list.size(); i++) {
            sb.append(INDENT_4_SPACES);
            sb.append(i);
            sb.append(") ");
            sb.append(list.get(i - 1));
        }
        return sb.toString();
    }
}
