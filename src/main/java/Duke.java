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
        Task[] tasks = new Task[100];

        // to keep track of num of task
        int itemNum = 1; // starts from 1

        //Duke will keep repeating until command given "Bye"
        while (!terminate) {
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                terminate = true; // terminates Duke
            } else if (text.equals("list")) {
                // print all the tasks stored currently in the list
                for (int i = 0; i < itemNum-1; i++) {
                    System.out.println((i+1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
            } else if (text.contains("done")) {
                String num = text.substring(5); // take out the int value of the task to be completed
                int listNum = Integer.parseInt(num); // changes to int
                Task hold = tasks[listNum-1];
                hold.markAsDone();

                // format
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + "[" + tasks[listNum-1].getStatusIcon() + "] " + tasks[listNum-1].description);
            }
            else {
                System.out.println("added: " + text);
                // create new instance of task and add to the list
                Task holder = new Task(text);
                tasks[itemNum-1] = holder; // position corresponds to item number
                itemNum++; // increase the item list count
            }
        }

        sc.close();

    }
}
