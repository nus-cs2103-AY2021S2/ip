import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("---------------------------------------\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        System.out.println("---------------------------------------" );

//        Level 1: Echo
        while(true) {
            Scanner scan = new Scanner(System.in);
            String input;
            input = scan.nextLine();

            if (input.equals("bye")) {
//            Terminate
                System.out.println("\n---------------------------------------" );
                System.out.println("Bye. Sayonara and goodbye!");
                System.out.println("---------------------------------------" );
                break;
            }
//            Echo:
            System.out.println("\n---------------------------------------" );
            System.out.println(input);
            System.out.println("---------------------------------------" );
        }
    }
}
