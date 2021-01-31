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


    /**
     * Surrounds the specified <code>input</code> with a asterisk border.
     *
     * @param input input to be bordered
     */
    public static void printBox(String input) {
        System.out.println(TOP_BORDER + INDENT_4_SPACES + input
                + "\n" + BOTTOM_BORDER);
    }

    public static String formatBox(String input) {
        return TOP_BORDER + INDENT_4_SPACES + input
                + "\n" + BOTTOM_BORDER;
    }

    public static String printList(TaskList list) {
        if (list.size() == 0 ) {
            return "OOPPS! Your Current Task List Is Empty!";
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

    public static void printSearch(ArrayList<Task> list) {
        System.out.print(TOP_BORDER);
        System.out.println("     Here are the matching tasks found: \n");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(INDENT_4_SPACES + i + ") "
                    + list.get(i - 1));
        }
        System.out.println(BOTTOM_BORDER);
    }
}
