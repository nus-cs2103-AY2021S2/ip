import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    static void display(String str) {
        String[] strings = str.split("\n");
        System.out.println("    " + "___________________________________________________________________");
        for (String s : strings) {
            System.out.println("    " + s);
        }
        System.out.println("    " + "___________________________________________________________________");
    }

    static void displayTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            String formattedTask = i + ". " + tasks.get(i - 1).toString();
            sb.append(formattedTask);
        }
        display(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display("Hello! I'm Duke\nWhat can I do for you?");
        while (sc.hasNextLine()) {
            String original = sc.nextLine();
            String[] command = original.split(" ");
            switch (command[0]) {
                case "bye":
                    display("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    displayTasks();
                    break;
                case "done":
                    Task toMarkDone = tasks.get(Integer.parseInt(command[1]) - 1);
                    toMarkDone.markDone();
                    display("Nice! I've marked this task as done:\n  " + toMarkDone);
                    break;
                default:
                    tasks.add(new Task(original));
                    display("added: " + original);
                    break;
            }
        }
    }
}
