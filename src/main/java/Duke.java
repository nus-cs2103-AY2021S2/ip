import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    static void display(String str) {
        String[] strings = str.split("\n");
        System.out.println("    " + "___________________________________________________________________");
        for (String s : strings) {
            System.out.println("     " + s);
        }
        System.out.println("    " + "___________________________________________________________________");
    }

    static void displayAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            String formattedTask = i + ". " + tasks.get(i - 1).toString();
            sb.append(formattedTask);
        }
        display(sb.toString());
    }

    static void displayAddedTask(Task task) {
        display("Got it. I've added this task:\n  "
                + task
                + "Now you have "
                + tasks.size()
                + " task(s) in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        display("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) break;
            respond(input);
        }
        display("Bye!");
        sc.close();
    }

    public static void respond(String input) {
        String[] command = input.split(" ");
        Task task;
        switch (command[0]) {
            case "list":
                displayAllTasks();
                break;
            case "done":
                Task toMarkDone = tasks.get(Integer.parseInt(command[1]) - 1);
                toMarkDone.markDone();
                display("Nice! I've marked this task as done:\n  " + toMarkDone);
                break;
            case "todo":
                task = new Todo(input);
                tasks.add(task);
                displayAddedTask(task);
                break;
            case "deadline":
                task = new Deadline(input);
                tasks.add(task);
                displayAddedTask(task);
                break;
            case "event":
                task = new Event(input);
                tasks.add(task);
                displayAddedTask(task);
                break;
        }
    }
}

