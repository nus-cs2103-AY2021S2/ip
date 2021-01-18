import java.util.Scanner;

public class DukeLevel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "    _________________________________________________";

        System.out.println(line);
        System.out.println("     Hello! I'm Duke :)");
        System.out.println("     What can I do for you?");
        System.out.println(line);

        Task[] tasksArray = new Task[100];
        int count = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] strArray = input.split(" ");
            String cmd = strArray[0];

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon! :)");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println("      " + (i+1) + ".[" + tasksArray[i].getStatusIcon() + "] " + tasksArray[i].description);
                }
                System.out.println(line);
            } else if (cmd.equalsIgnoreCase("done")) {
                int cmdNum = Integer.parseInt(strArray[1]);
                System.out.println(line);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [" + tasksArray[cmdNum-1].getStatusIcon() + "] " + tasksArray[cmdNum-1].description);
                System.out.println(line);
                tasksArray[cmdNum-1].markAsDone();
            } else {
                tasksArray[count] = new Task(input);
                System.out.println(line);
                System.out.println("     " + "added : " + input);
                System.out.println(line);
                count++;
            }
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
}