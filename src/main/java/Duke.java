import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // task list
        ArrayList<Task> ls = new ArrayList<>();

        // greet
        System.out.println(formatMessage("Hello! I'm Duke\nWhat can I do for you?"));

        // setup scanner for inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // input loop
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // list
                printList(ls);
            } else {
                String[] splitInput = input.split(" ");
                if (splitInput.length == 2 && splitInput[0].equals("done")) {
                    // done
                    int taskNum = Integer.parseInt(splitInput[1]);
                    Task finishedTask = ls.get(taskNum - 1);
                    finishedTask.setCompletion(true);
                    System.out.println(formatMessage("Nice! I've marked this task as done:\n   " + finishedTask));
                } else {
                    // add
                    System.out.println(formatMessage("added: " + input));
                    ls.add(new Task(input));
                }
            }
            input = sc.nextLine();
        }

        // exit
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
        sc.close();
    }

    // prints list item number and string
    public static void printList(ArrayList<Task> ls) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < ls.size(); i++) {
            System.out.println("     " + (i + 1) + "." + ls.get(i).toString());
        }
        System.out.println("    ____________________________________________________________\n");
    }

    // format for greeting, echo and exit
    public static String formatMessage(String message) {
        String newMessage = message.replaceAll("\n", "\n     ");
        return ("    ____________________________________________________________\n" + "     " + newMessage + "\n"
                + "    ____________________________________________________________\n");
    }
}
