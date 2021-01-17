import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "               |  _ \\ _   _| | _____ \n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    -------------------------------------------------\n"
                + "     Hello! I'm"
                + logo
                + "\n     What can I do for you?\n"
                + "    -------------------------------------------------");

        boolean exit = false;
        while (!exit) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                exit = true;
                System.out.println("    -------------------------------------------------\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "    -------------------------------------------------");
            } else {
                System.out.println("    -------------------------------------------------\n"
                        + "     "
                        + userInput
                        + "\n    -------------------------------------------------");
            }
        }

        sc.close();
    }
}
