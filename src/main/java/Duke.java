import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void runDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        scan();
    }

    public void scan() {
        Scanner s = new Scanner(System.in);
        String description = s.nextLine();
        while(!description.equals("bye")) {
            manageTask(description);
            description = s.nextLine();
        }
        manageTask(description);
        s.close();
    }

    public void manageTask(String description) {
        System.out.println("     --------------------------------");
        if (description.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String rank = String.valueOf(i + 1);
                System.out.println("     " + rank + "." + tasks.get(i));
            }
        } else if (description.equals("bye")){
            System.out.println("     Bye.Cya");
        } else if (description.contains("done")) {
            String taskLocation = description.substring(5);
            int taskNumber = Integer.parseInt(taskLocation);
            Task doneTask = tasks.get(taskNumber - 1);
            doneTask.markAsDone();
            System.out.println("     Noice! I've marked this task as done:");
            System.out.println("     " + doneTask);
        } else {
            Task newTask = new Task(description);
            tasks.add(newTask);
            System.out.println("     added: " + newTask.getDescription());
        }
        System.out.println("     --------------------------------");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
