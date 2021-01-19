import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String lines = "    ______________________________________________";
    private static String indent = "      ";
    private static String taskIndent = "  "; // just an extra indent for listing tasks

    /**
     * Mark specified task done
     * @param i off-by-one index of a task in array list
     */
    private static void markDone(int i) {
        taskList.get(i - 1).markAsDone();
        print(new String[]{"Good work! I've marked this task done:",
                taskIndent + taskList.get(i - 1)});
    }

    private static void printTaskList() {
        System.out.println(lines);

        System.out.println(indent + "Your tasks:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(taskIndent);
            System.out.println(indent + (i + 1) + "." + taskList.get(i));
        }

        System.out.println(lines);
    }

    // helper method to format chat bot responses
    // prints all strings in messages array in a separate indented line
    private static void print(String[] messages) {
        System.out.println(lines);

        for (int i = 0; i < messages.length; i++) {
            System.out.println(indent + messages[i]);
        }

        System.out.println(lines);
    }
    
    private static void addTask(Task t) {
        taskList.add(t);
        String[] messages = {
                "Success. I've added this task:",
                taskIndent + t // standardize this indent,
        };
        print(messages);
    }

    // parse done, todos, deadline or event commands
    private static void parseCommand(String userInput) {
        int firstSpaceIndex = userInput.indexOf(" ");
        String firstWord = userInput.substring(0, firstSpaceIndex); // don't include the space

        if (firstWord.equals("done")) {
            int secondArg = Integer.parseInt(userInput.substring(firstSpaceIndex + 1).trim());
            markDone(secondArg);
        } else if (firstWord.equals("todo")) {
            String secondArg = userInput.substring(firstSpaceIndex + 1).trim();
            addTask(new Todo(secondArg));
        } else if (firstWord.equals("deadline")) {

            int byIndex = userInput.indexOf("/by"); // assuming valid
            String desc = userInput.substring(firstSpaceIndex + 1, byIndex - 1).trim();
            String deadline = userInput.substring(byIndex + 3).trim();

            addTask(new Deadline(desc, deadline));

        } else if (firstWord.equals("event")) {

            int atIndex = userInput.indexOf("/at"); // assuming valid
            String desc = userInput.substring(firstSpaceIndex + 1, atIndex - 1).trim();
            String timing = userInput.substring(atIndex + 3).trim();

            addTask(new Event(desc, timing));
        }

        // catch exceptions where substring end is wrong i.e. extra arguments not found?
    }

    public static void main(String[] args) {
        String logo =
            " ______\n"
            + "/______\\ Kiwi's\n"
            + "|______|     Inn\n"
            + "####################";

        Scanner sc = new Scanner(System.in);

        // intro message
        System.out.println(logo);
        print(new String[]{"Welcome, traveller. I'm Kiwi.", "What would you like to do today?"});

        // variables to reuse
        String userInput;
        String[] toPrint = new String[1];
        String bye = "bye";

        while (true) {
            userInput = sc.nextLine().trim();
            if (userInput.equals(bye)) {
                toPrint[0] = "Bye. See you again soon!";
                print(toPrint);
                sc.close();
                break;
            } else if (userInput.equals("list")) {
                printTaskList();
            } else {
                // assumed to be a valid command and have space
                parseCommand(userInput);
            }
        }
    }
}
