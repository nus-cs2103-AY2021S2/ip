import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String input;

        // Welcomes user
        greeting();

        // Receive action
        do {
            System.out.print("<< ");
            input = sc.nextLine().toLowerCase();

            if (input.equals("bye"))
                exit = exit();
            else
                echo(input);
        }
        while (!exit);
    }


    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";

        System.out.println("____________________________________________________________");
        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println(">> Hey, hello there! I'm Duke, your personal chat bot.");
        System.out.println(">> Is there anything I can do for you today?");
    }

    public static void echo(String input) {
        System.out.println(">> " + input);
    }

    public static boolean exit() {
        System.out.println(">> Alright, take care. I hope to see you again soon!");
        return true;
    }
}
