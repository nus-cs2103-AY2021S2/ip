import java.util.Scanner;
import java.util.ArrayList;

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
        } else if (commandWord.equals("todo")){
            this.addToDo(command.substring(5));
        }
    }

    public void addToDo(String taskName) {
        ToDo newTask = new ToDo((taskName));
        this.storedTasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        if (this.storedTasks.size() == 1) {
            System.out.println("Now you have " + this.storedTasks.size() + " task in the list");
        } else {
            System.out.println("Now you have " + this.storedTasks.size() + " tasks in the list");
        }
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < storedTasks.size(); i++) {
            Task currTask = storedTasks.get(i);
            System.out.println(i + 1 + "." + currTask);
        }
    }

    public void doneTask(int index) {
        Task currTask = this.storedTasks.get(index - 1);
        currTask.completeTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask);
    }

}
