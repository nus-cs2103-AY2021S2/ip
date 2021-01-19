import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke.printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke.printHorizontalLine();
        while(true) {
            String s = sc.nextLine();
            if(!s.equals("bye")) {
                Duke.printHorizontalLine();
                System.out.println(s);
                Duke.printHorizontalLine();
            } else {
                Duke.printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                Duke.printHorizontalLine();
                break;
            }
        }
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }


    /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n" + logo);*/
}
