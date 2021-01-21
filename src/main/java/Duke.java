import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner scanner = new Scanner(System.in);     

        user_active: while (true) {
            String usercommand = scanner.nextLine();
            String[] commands = usercommand.split(" ");

            switch (commands[0]) {
                case "bye":
                    byebye();
                    break user_active;

                default: // echo
                    System.out.println(usercommand + "\n");
            }
        }

        scanner.close();
    }

    public static void greet() {
        System.out.println("What can I do for you?\n");
    }

    public static void byebye() {
        System.out.println("ByeBye. Hope to see you again soon!");
    }

}
