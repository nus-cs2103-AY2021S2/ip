package simulator;

import task.Task;

import java.util.List;

public class Design {
    public static String markMsg = "You have marked this task as completed: \n";
    public static String addMsg = "Roger that! Added the following task: \n \n      ";
    public static String deleteMsg = "Roger that! Deleted the follow task: \n \n      ";
    public static String indent4 = "     ";
    public static String topBorder = "\n    ********************************* \n";
    public static String btmBorder = "    ********************************* \n";

    public static String welcomeMsg = "         █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n"
            + "         █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
            + "         █░░║║║╠─║─║─║║║║║╠─░░█ \n"
            + "         █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
            + "         █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
    public static String logo = "         ──────▄▀▄─────▄▀▄ \n"
            + "         ─────▄█░░▀▀▀▀▀░░█▄ \n"
            + "         ─▄▄──█░░░░░░░░░░░█──▄▄ \n"
            + "         █▄▄█─█░░▀░░┬░░▀░░█─█▄▄█ ";

    public static String greeting = "Hey there! I'm Kawaii Kat \n"
            + "     How can i assist you ~nya~?";

    public static String exitMessage = "Farewell. See you soon :)!";

    public static void printBox(String input) {
        System.out.println(topBorder + indent4 + input
                + "\n" + btmBorder);
    }

    public static void printList(List<Task> list) {
        System.out.print(topBorder);
        System.out.println("     This is your present task list: \n");
        for (int i = 1; i <= list.size(); i++) {
            if (i == list.size()) {
                System.out.println(indent4 + i + ") "
                        + list.get(i - 1));
            } else {
                System.out.println(indent4 + i + ") "
                        + list.get(i - 1));
            }
        }
        System.out.println(btmBorder);
    }
}
