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
            try {
                listen();
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }
    }

    private void listen() throws DukeException {
        String input = scanner.nextLine();
        if(input.equals("list")) {
            printList();
        } else if (input.startsWith("done")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            markAsDone(itemNo);
        } else if (input.startsWith("todo")) {
            addTask(Todo.parse(input));
        } else if (input.startsWith("deadline")) {
            addTask(Deadline.parse(input));
        } else if (input.startsWith("event")) {
            addTask(Event.parse(input));
        } else if (input.equals("bye")) {
            bye();
        } else {
            String error = "Sorry! I don't know what that means.";
            throw new DukeException(error);
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

