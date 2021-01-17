import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String indent = "         ";
        String horizSep = indent + "________________________________________________\n";

        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizSep + greeting + horizSep);

        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {

                System.out.println(horizSep + farewell + horizSep);
                sc.close();
                return;
            } else {
                System.out.println(horizSep + indent + " " + next + "\n" + horizSep);
            }
        }

    }
}
