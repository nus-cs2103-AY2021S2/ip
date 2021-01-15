import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<String> list = new ArrayList<>();

        greet();
        addList(list);
    }

    //Duke greets the user.
    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    //Echos the input back to the user.
    public static void echo() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                    + "\t" + input + "\n"
                    + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Adds the input in a list and echo it back to the user.
    //Prints the list if input is "list"
    public static void addList(List list) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList(list);
            } else {
                list.add(input);
                System.out.println("\t____________________________________________________________\n"
                                + "\tadded: " + input + "\n"
                                + "\t____________________________________________________________\n");
            }
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Prints out the items in the list.
    public static void showList(List list) {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, list.get(i));
        }
        System.out.println("\t____________________________________________________________\n");
    }
}
