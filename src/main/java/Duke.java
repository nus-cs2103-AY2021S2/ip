import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
    
    // Constants
    private static final String LINE = "____________________________________________________________";

    // Attributes
    private Scanner scanner;
    private boolean isActive;
    private ArrayList<Task> taskList;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
        this.taskList = new ArrayList<>();
    }

    public void start() {
        String greeting = "Hello! I'm Duke" + "\n"
        + "What can I do for you?";
        print(greeting);

        while(isActive) {
            listen();
        }
    }

    private void listen() {
        String input = scanner.nextLine();
        if(input.equals("list")) {
            printList();
        } else if (input.startsWith("done")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            Task selected = taskList.get(itemNo - 1);
            selected.markAsDone();
            String output = "Marked as done: \n" + selected;
            print(output);
        } else if (input.equals("bye")) {
            isActive = false;
            String output = "Bye. Hope to see you again soon!";
            print(output);
        } else {
            addToList(new Task(input));
            print("Added: " + input);
        }
    }

    private void printList() {
        String output = "";
        for(int i = 0; i < taskList.size(); i++) {
            output += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        print(output);
    }

    private void addToList(Task task) {
        this.taskList.add(task);
    }

    private void print(String input) {
        System.out.println(LINE + "\n" + input + "\n" + LINE + "\n");
    }
}

