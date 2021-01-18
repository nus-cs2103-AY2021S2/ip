import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    ArrayList<ListObject> list;

    public Duke() {
        greet();
        this.list = new ArrayList<>();
    }

    private void printWithStyle(String[] output) {
        System.out.println("    ________________________________________________________________");
        for (String str : output) {
            System.out.println("    " + str);
        }
        System.out.println("    ________________________________________________________________");
    }

    private void printWithStyle(String output) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + output );
        System.out.println("    ________________________________________________________________");
    }

    void greet() {
        printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }

    void handleInput(String userInput) {
        if (userInput.equals("list")) {
            printList();
        } else if (userInput.trim().split("\\s+")[0].equals("done")) {
            int doneTaskNumber = Integer.parseInt(userInput.trim().split("\\s+")[1]);
            printDone(doneTaskNumber);
        } else {
            printWithStyle("added: " + userInput);
            this.list.add(new ListObject(userInput));
        }
    }

    void printDone(int taskNumber) {
        ListObject task = this.list.get(taskNumber - 1);
        task.done();
        printWithStyle(new String[] {"Nice! I've marked this task as done:", task.toString()});
    }

    void printList() {
        String[] printedArray = new String[this.list.size() + 1];
        printedArray[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.list.size(); i++) {
            String listEntry = String.valueOf(i + 1) + "." +
                    this.list.get(i).toString();
            printedArray[i + 1] = listEntry;
        }
        printWithStyle(printedArray);
    }

    void bye() {
        printWithStyle("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            duke.handleInput(input);
            input = scanner.nextLine();
        }
        duke.bye();

    }
}
