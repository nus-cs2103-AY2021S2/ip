import java.util.*;

public class Duke {
    public static String topBorder = "\n    ********************************* \n";
    public static String btmBorder = "    ********************************* \n";
    public static ArrayList<Task> list = new ArrayList<>();
    public static String doneMsg = "You have marked this task as completed: \n";

    public static void printBox(String input) {
        System.out.println(topBorder + "     " + input + "\n" + btmBorder);
    }

    public static void printList(List<Task> list) {
        System.out.println(topBorder);
        for (int i = 1; i <= list.size(); i++) {
            if (i == list.size()) {
                System.out.println("     " + i + ") " + list.get(i - 1));
            } else {
                System.out.println("     " + i + ") " + list.get(i - 1) + "\n");
            }
        }

        System.out.println(btmBorder);
    }

    public static void process(String command) {
        if (command.equals("list")) {
            printList(list);
        } else {
            if (command.split("\\s+")[0].equals("done")) {
                int index = Integer.parseInt(command.split("\\s+")[1]);
                Task task = list.get(index - 1);
                task.complete();
                printBox(doneMsg + "      " + task);
            } else {
                Task newTask = new Task(command);
                list.add(newTask);
                printBox("added: " + newTask);
            }
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