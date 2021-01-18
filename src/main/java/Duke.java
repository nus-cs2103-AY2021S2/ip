import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    ArrayList<String> taskList = new ArrayList<>();

    public void run() {
        System.out.println(makeOutput("Hello! I'm Duke\nWhat can I do for you?"));

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else {
                addTask(input);
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
        for (int i = 0; i < taskList.size(); i++) {
            output += String.valueOf(i + 1) + ". " + taskList.get(i);
            if (i != taskList.size() - 1) output += "\n";
        }

        System.out.println(makeOutput(output));
    }

    public void addTask(String task) {
        taskList.add(task);
        System.out.println(makeOutput("added: " + task));
    }

}
