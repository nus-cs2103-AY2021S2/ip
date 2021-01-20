import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    ArrayList<Task> taskList = new ArrayList<>();

    public void run() {
        System.out.println(makeOutput("Hello! I'm Duke\nWhat can I do for you?"));

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    listTasks();
                } else if (input.length() >= 5 && input.substring(0, 5).equals("done ")) {
                    int id = Integer.valueOf(input.substring(5));
                    completeTask(id);
                } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    addTask(Task.createTask(input));
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    addTask(Event.createEvent(input));
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    addTask(Deadline.createDeadline(input));
                } else {
                    System.out.println(makeOutput("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
            } catch (DukeException e) {
                System.out.println(makeOutput(e.getMessage()));
            }
            
            input = sc.nextLine();
        }
        System.out.println(makeOutput("Bye. Hope to see you again soon!"));

        sc.close();
    }

    public String makeOutput(String input) {
        return "\t____________________________________________________________"
            + "\n\t " + input.replace("\n", "\n\t ")
            + "\n\t____________________________________________________________";
    }

    public void listTasks() {
        String output = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            output += String.valueOf(i + 1) + ". " + this.taskList.get(i);
            if (i != this.taskList.size() - 1) output += "\n";
        }

        System.out.println(makeOutput(output));
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println(makeOutput("Got it. I've added this task: \n\t" 
            + task + String.format("\nNow you have %d tasks in the list.", this.taskList.size())));
    }

    public void completeTask(int id) {
        int realId = id - 1;
        this.taskList.set(realId, this.taskList.get(realId).completeTask());
        System.out.println(makeOutput("Nice! I've marked this item as done:\n\t" 
            + this.taskList.get(realId)));
    }

}
