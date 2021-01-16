import java.util.*;

public class Duke {
    public static String topBorder = "\n    ********************************* \n";
    public static String btmBorder = "    ********************************* \n";
    public static ArrayList<String> list = new ArrayList<>();

    public static void printBox(String input) {
        System.out.println(topBorder + "     " + input + "\n" + btmBorder);
    }

    public static void process(String command) {
        if (command.equals("list")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= list.size(); i++) {
                if (i == list.size()) {
                    sb.append("     " + i + ") " + list.get(i - 1));
                } else {
                    if (i == 1) {
                        sb.append(i + ") " + list.get(i - 1) + "\n");
                    } else {
                        sb.append("     " + i + ") " + list.get(i - 1) + "\n");

                    }
                }
            }
            printBox(sb.toString());
        } else {
            list.add(command);
            printBox("added: " + command);
        }
    }

    public static void main(String[] args) {
        String welcomeMsg = "         █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n" + "         █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
                + "         █░░║║║╠─║─║─║║║║║╠─░░█ \n" + "         █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
                + "         █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
        String logo = "         ──────▄▀▄─────▄▀▄ \n" + "         ─────▄█░░▀▀▀▀▀░░█▄ \n"
                + "         ─▄▄──█░░░░░░░░░░░█──▄▄ \n" + "         █▄▄█─█░░▀░░┬░░▀░░█─█▄▄█ ";

        String greeting = "Hey there! I'm Kawaii Kat \n" + "     How can i assist you nya~?";

        String exitMessage = "Farewell. See you soon :)!";

        System.out.println(welcomeMsg + logo);
        printBox(greeting);

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals(("bye"))) {
            process(input);
            input = sc.nextLine();
        }

        printBox(exitMessage);

    }
}