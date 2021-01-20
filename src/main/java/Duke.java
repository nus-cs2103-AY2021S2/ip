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
            markAsDone(itemNo);
        } else if (input.startsWith("todo")) {
            addTask(new Todo(input));
        } else if (input.startsWith("deadline")) {
            String[] parts = input.split("/by");
            String desc = parts[0].strip();
            String by = parts[1].strip();
            addTask(new Deadline(desc, by));
        } else if (input.startsWith("event")) {
            String[] parts = input.split("/at");
            String desc = parts[0].strip();
            String at = parts[1].strip();
            addTask(new Event(desc, at));
        } else if (input.equals("bye")) {
            bye();
        } else {

        }
    }

    private void markAsDone(int itemNo) {
        Task selected = taskList.get(itemNo - 1);
        selected.markAsDone();
        String output = "Marked as done: \n" + selected;
        print(output);
    }

    private void printList() {
        String output = "";
        for(int i = 0; i < taskList.size(); i++) {
            output += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        print(output);
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        String output = "I've added this task: \n" + task.toString()
        + "\n There are now " + taskList.size() + " tasks in the list.";
        print(output);
    }

    private void bye() {
        this.isActive = false;
        String output = "Bye. Hope to see you again soon!";
        print(output);
    }

    private void print(String input) {
        System.out.println(LINE + "\n" + input + "\n" + LINE + "\n");
    }
}

