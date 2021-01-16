import java.util.*;

public class Duke {
    public static String border = "    ********************************* \n";

    public static void process(String input) {
        System.out.println(border + "     " + input + "\n" + border);
    }

    public static void main(String[] args) {
        String welcomeMsg = "         █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n" + "         █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
                + "         █░░║║║╠─║─║─║║║║║╠─░░█ \n" + "         █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
                + "         █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
        String logo = "         ──────▄▀▄─────▄▀▄ \n" + "         ─────▄█░░▀▀▀▀▀░░█▄ \n"
                + "         ─▄▄──█░░░░░░░░░░░█──▄▄ \n" + "         █▄▄█─█░░▀░░┬░░▀░░█─█▄▄█";

        String greeting = "     Hey there! I'm Kawaii Kat \n" + "     How can i assist you nya~? \n";

        String exitMessage = "     Farewell. See you soon :)! \n";

        System.out.println(welcomeMsg + logo + "\n" + border + greeting + border);

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals(("bye"))) {
            process(input);
            input = sc.nextLine();
        }

        System.out.println(border + exitMessage + border);

    }
}