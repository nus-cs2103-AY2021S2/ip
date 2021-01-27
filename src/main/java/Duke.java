import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    ArrayList<Task> storedTasks;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Duke iceBear = new Duke();
        while (true) {
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                iceBear.performCommand(nextCommand);
            }
        }
    }

    public Duke() {
        this.storedTasks = new ArrayList<>();
        System.out.println("Hello! I'm Icebear");
        System.out.println("What can I do for you?");
    }

    public void performCommand(String command) {
        String commandWord = command.split(" ")[0];
        if (commandWord.equals("list")) {
            this.listTask();
        } else if (commandWord.equals("done")) {
            this.doneTask(Integer.valueOf(command.split(" ")[1]));
        } else {
            this.addTask(command);
        }
    }

    public void addTask(String taskName) {
        this.storedTasks.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTasks.size(); i++) {
            Task currTask = storedTasks.get(i);
            String doneCross = " ";
            if (currTask.isComplete()) {
                doneCross = "X";
            }
            System.out.println(i + 1 + ".[" + doneCross + "] " + currTask);
        }
    }

    public void doneTask(int index) {
        Task currTask = this.storedTasks.get(index - 1);
        currTask.completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + currTask);
    }

}