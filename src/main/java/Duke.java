import java.util.Scanner;

public class Duke {

    public static void Greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke, your favourite penguin.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

    }

    public static void Echo() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String exitCommand = "bye";
        if (!(command.toLowerCase()).equals(exitCommand)) {
            System.out.println("____________________________________________________________\n"
                    + command
                    + "\n____________________________________________________________\n");
            Echo();
        } else {
            Exit();
        }
    }

    public static void Exit() {
            String exit = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "Wish you all the best for CS2103T\n"
            + "____________________________________________________________\n";

            System.out.println(exit);

        }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Greet();

        Echo();
    }
}
