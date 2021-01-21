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
        chat(inputs);
    }

    public static void chat(ArrayList<String> inputs) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            scanner.close();
        } else if (input.equals("list")) {
            for (int i = 0; i < inputs.size(); i++) {
                System.out.println(i + 1 + ". " + inputs.get(i));
            }
            chat(inputs);
        } else {
            ArrayList<String> newinputs = new ArrayList<String>(inputs);
            newinputs.add(input);
            System.out.println("added: " + input + "\n");
            chat(newinputs);
        }
    }
}
