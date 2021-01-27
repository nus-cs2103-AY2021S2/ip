import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);

    public static void echo(String str) {
        System.out.println("------------------------------");
        System.out.println(str);
        System.out.println("------------------------------");
    }

    public static void greet() {
        String logo = "______                    _       _           _\n"
                + "|  ___|                  (_)     | |         | |\n"
                + "| |_ _ __ __ _ _ __   ___ _ ___  | |     ___ | |__\n"
                + "|  _| '__/ _` | '_ \\ / __| / __| | |    / _ \\| '_ \\\n"
                + "| | | | | (_| | | | | (__| \\__ \\ | |___| (_) | | | |\n"
                + "\\_| |_|  \\__,_|_| |_|\\___|_|___/ \\_____/\\___/|_| |_|\n"
                + "\n"
                + "\n";
        System.out.println("Hello from\n" + logo + "(a.k.a Loh Jing Yen)");
        System.out.println("What can I do for you?");
        System.out.println("Enter a command below for me to assist you");
    }

    public static String readCommand() {
        return in.nextLine().toLowerCase();
    }
}
