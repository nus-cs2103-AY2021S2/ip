import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo =
                "     ______\n" +
                        "    |___  /\n" +
                        "       / / \n" +
                        "      / /  \n" +
                        "     / /__ \n" +
                        "    /_____|\n";

        System.out.println("\n~ Hello! I am Zee :) ~\n"
                + logo + "\n"
                + "~ What can I do for you today? ~\n");

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("\n~ " + command + " ~\n");
            command = scanner.nextLine();
        }

        scanner.close();
        System.out.println("\n~ Bye! Come back soon! UwU ~\n");
    }
}
