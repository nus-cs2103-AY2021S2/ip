import java.util.*;

public class Duke {
    public static void main(String[] args) {

        /*
        ***VANILLA ip-master CODE***
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        boolean terminate = false;

        //Duke will keep repeating until command given "Bye"
        while (!terminate) {
            String text = sc.next();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                terminate = true; // terminates Duke
            } else {
                System.out.println(text);
            }
        }

        sc.close();

    }
}
