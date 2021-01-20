import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> collection = new ArrayList<Task>();
        Boolean exit = false;
        String input;

        // Welcomes user
        greeting();

        // Receive action
        do {
            System.out.print("<< ");
            input = sc.nextLine();

            if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event"))
                add(collection, input);
            else if (input.toLowerCase().startsWith("done"))
                done(collection, input);
            else if (input.toLowerCase().equals("list"))
                list(collection);
            else if (input.toLowerCase().equals("bye"))
                exit = exit();
        } while (!exit || sc.hasNextLine());
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

    public static void add(ArrayList<Task> collection, String input) {
        // Parse input
        String[] inputArr = input.split(" ");
        String taskType = inputArr[0];
        String taskDesc = "";
        String taskArg = "";

        if (taskType.equals("todo")) {
            for (int i = 1; i < inputArr.length; i++) {
                if (!taskDesc.equals(""))
                    taskDesc += " ";
                taskDesc += inputArr[i];
            }
            collection.add(new Todo(taskDesc));
        }
        else if (taskType.toLowerCase().equals("deadline") || taskType.toLowerCase().equals("event")) {
            // Get description
            int i;
            for (i = 1; i < inputArr.length; i++) {
                if (inputArr[i].toLowerCase().equals("/by") || inputArr[i].toLowerCase().equals("/at")) {
                    break;
                } else {
                    if (!taskDesc.equals(""))
                        taskDesc += " ";
                    taskDesc += inputArr[i];
                }
            }
            // Get argument
            for (i = i + 1; i < inputArr.length; i++) {
                if (!taskArg.equals(""))
                    taskArg += " ";
                taskArg += inputArr[i];
            }

            // Add to collection
            if (taskType.toLowerCase().equals("deadline")) {
                collection.add(new Deadline(taskDesc, taskArg));
            } else if (taskType.toLowerCase().equals("event")) {
                collection.add(new Event(taskDesc, taskArg));
            }
        }

        System.out.println(">> Got it, I have added the task \"" + taskDesc + "\" to your collection.");
    }

    public static void done(ArrayList<Task> collection, String input) {
        int itemIdx = Integer.parseInt(input.split(" ")[1]) - 1;

        // Check if the item index is in range
        if (itemIdx >= 0 && itemIdx < collection.size()) {
            collection.get(itemIdx).markAsDone();
            System.out.println(">> Task \"" + collection.get(itemIdx).getDescription() + "\" is marked as done.");
        } else {
            System.out.println(">> I don't think there is such an item...");
        }
    }

    public static void list(ArrayList<Task> collection) {
        System.out.println(">> You got a total of " + collection.size() + " task(s).");
        for (int i = 0; i < collection.size(); i++) {
            String formattedString = String.format("\t%d. %s", i + 1, collection.get(i));
            System.out.println(formattedString);
        }
    }

    public static boolean exit() {
        System.out.println(">> Alright, take care. I hope to see you again soon!");
        return true;
    }

}
