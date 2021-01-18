import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) { 
            String input = sc.next();

            if (input.equals("bye")) { 
                textWarper("Bye. Hope to see you again soon!");
                break;
            } else { 
                textWarper(input);
            }
        }

    }

    public static void textWarper(String a) { 
        System.out.println(" ____________________________________________________________");
        System.out.println("  " + a);
        System.out.println(" ____________________________________________________________");
    }
}

