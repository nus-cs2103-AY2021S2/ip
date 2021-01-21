import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke. What can I do for you?\n");
        ArrayList<String> inputs = new ArrayList<String>();
        chat(inputs, 0);
    }

    public static void chat(ArrayList<String> inputs, int taskno) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            scanner.close();
        } else if (input.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < inputs.size(); i++) {
                System.out.println(i + 1 + "." + inputs.get(i));
            }
            chat(inputs, inputs.size() - 1);
        } else if (input.substring(0, 4).equals("done")) {
            int tasknum = Integer.parseInt(input.substring(5));
            System.out.println("Nice! I've marked this task as done:");
            String newstring = "[X] " + inputs.get(tasknum - 1).substring(4);
            System.out.println(newstring);
            ArrayList<String> newinputs = new ArrayList<String>(inputs);
            newinputs.set(tasknum - 1, newstring);
            chat(newinputs, inputs.size() - 1);
        } else {
            ArrayList<String> newinputs = new ArrayList<String>(inputs);
            newinputs.add("[ ] " + input);
            System.out.println("added: " + input + "\n");
            chat(newinputs, inputs.size() - 1);
        }
    }
}
