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

        // Starting line for UI
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");

        // Condition for Duke to stop
        boolean terminate = false;

        // create LinkedList to store information of user inputs
        LinkedList<String> tasks = new LinkedList<>();

        //Duke will keep repeating until command given "Bye"
        while (!terminate) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                terminate = true; // terminates Duke
            } else if (text.equals("list")) {
                // print all the tasks stored currently in the list
                int itemNum = 1; // starts from 1
                while(!tasks.isEmpty()) {
                    System.out.println(itemNum + ". " + tasks.poll());
                    itemNum++;
                }
            }
            else {
                System.out.println("added: " + text);
                tasks.add(text);
            }
        }

        sc.close();

    }
}
