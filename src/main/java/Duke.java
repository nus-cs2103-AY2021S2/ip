import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<String> collection = new ArrayList<String>();
        boolean exit = false;

        // Welcomes user
        greeting();

        // Receive action
        do {
            System.out.print("<< ");
            input = sc.nextLine().toLowerCase();

            if (input.equals("list"))
                list(collection);
            else if (input.equals("bye"))
                exit = exit();
            else
                add(collection, input);
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

    public static void list(ArrayList<String> collection) {
        System.out.println(">> Got it, this is your list.");
        for (int i = 0; i < collection.size(); i++) {
            String formattedString = String.format("\t%d. %s", i + 1, collection.get(i));
            System.out.println(formattedString);
        }
    }

    public static boolean exit() {
        System.out.println(">> Alright, take care. I hope to see you again soon!");
        return true;
    }

    public static void add(ArrayList<String> collection, String input) {
        collection.add(input);
        System.out.println(">> Added: " + input);
    }
}
