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
    private TaskList taskList;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
        this.taskList = TaskList.createFromStorage(new Storage());
    }

    public void start() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?";
        print(greeting);

        while (isActive) {
            try {
                listen();
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }
    }

    private void listen() throws DukeException {
        String input = scanner.nextLine();
        if (input.equals("list")) {
            print(taskList.toString());
        } else if (input.startsWith("done")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            markAsDone(itemNo);
        } else if (input.startsWith("delete")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            deleteFromList(itemNo);
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
        Task selected = taskList.markAsDone(itemNo - 1);
        String output = "Marked as done: \n" + selected;
        print(output);
    }

    private void deleteFromList(int itemNo) {
        Task selected = taskList.delete(itemNo - 1);
        String output = "I removed this task: \n" + selected;
        print(output);
    }

    private void addTask(Task task) {
        this.taskList.add(task);
        String output = "I've added this task: \n" + task.toString() + "\n There are now " + taskList.size()
                + " tasks in the list.";
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
