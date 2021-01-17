import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        List<String> myList = new ArrayList<>();
        String indent = "         ";
        String horizSep = indent + "________________________________________________";

        String greeting = indent + " Hello! I'm Duke\n" + indent + " What can I do for you?\n";
        String farewell = indent + " Bye. Hope to see you again soon!\n";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizSep + "\n" + greeting + horizSep + "\n");

        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {

                System.out.println(horizSep + "\n" + farewell + horizSep + "\n");
                sc.close();
                return;
            } else if (next.equals("list")) {
                ListIterator iter = myList.listIterator();

                System.out.println(horizSep);
                while (iter.hasNext()) {
                    System.out.println(indent + " " + String.valueOf(iter.nextIndex() + 1) + ". " + iter.next());
                }
                System.out.println(horizSep);

            } else {
                myList.add(next);
                System.out.println(horizSep + "\n" +  indent + " added: " + next + "\n" + horizSep + "\n");
            }
        }

    }
}
