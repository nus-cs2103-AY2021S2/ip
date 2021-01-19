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
            System.out.println("     Got it. I've added this task:");
            if (description.contains("todo")){
                String descLocation = description.substring(5);
                ToDo newToDo = new ToDo(descLocation);
                tasks.add(newToDo);
                System.out.println("       " + newToDo);
            } else if (description.contains("deadline")){
                String descLocation = description.substring(9);
                int byPosition = descLocation.indexOf("/by");
                String desc = descLocation.substring(0, byPosition);
                String by = descLocation.substring(byPosition + 4);
                Deadline newDeadline = new Deadline(desc, by);
                tasks.add(newDeadline);
                System.out.println("       " + newDeadline);
            } else if (description.contains("event")) {
                String descLocation = description.substring(6);
                int atPosition = descLocation.indexOf("/at");
                String desc = descLocation.substring(0, atPosition);
                String at = descLocation.substring(atPosition + 4);
                Event newEvent = new Event(desc, at);
                tasks.add(newEvent);
                System.out.println("       " + newEvent);
            }
            String listLength = Integer.toString(tasks.size());
            System.out.println("     Now you have " + listLength + " tasks in the list!");
        }
        System.out.println("     --------------------------------");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
