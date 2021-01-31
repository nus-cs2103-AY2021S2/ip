import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
                } else if (input.equals("save")) {
                    save();
                } else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    if (input.length() <= 5) {
                        throw new DukeException("☹ OOPS!!! Please enter a task id to complete!");
                    }
                    int id = Integer.valueOf(input.substring(5));
                    completeTask(id);
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    if (input.length() <= 7) {
                        throw new DukeException("☹ OOPS!!! Please enter a task id to delete!");
                    }
                    int id = Integer.valueOf(input.substring(7));
                    deleteTask(id);
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

    public void completeTask(int id) throws DukeException {
        int realId = id - 1;
        if (realId < 0 || realId >= this.taskList.size()) {
            throw new DukeException("☹ OOPS!!! You have selected an incorrect task id! You can view task ids via \"list\"");
        }
        this.taskList.set(realId, this.taskList.get(realId).completeTask());
        System.out.println(makeOutput("Nice! I've marked this item as done:\n\t" 
            + this.taskList.get(realId)));
    }

    public void deleteTask(int id) throws DukeException {
        int realId = id - 1;
        if (realId < 0 || realId >= this.taskList.size()) {
            throw new DukeException("☹ OOPS!!! You have selected an incorrect task id! You can view task ids via \"list\"");
        }
        Task task = this.taskList.get(realId);
        this.taskList.remove(realId);
        System.out.println(makeOutput("Noted. I've removed this task:\n\t"
            + task + String.format("\nNow you have %d tasks in the list.", this.taskList.size())));
    }

    public void save() {
        try (PrintStream out = new PrintStream(new FileOutputStream("./data/duke.txt"))) {
            String output = taskList.toString()
                .replace(", ", "\n");
            output = output.substring(0, output.length() - 1).substring(1);
            out.print(output);
            System.out.println(makeOutput("File successfully saved!"));
        } catch (FileNotFoundException e) {
            System.out.println(makeOutput(e.getMessage()));
        }
    }

}
