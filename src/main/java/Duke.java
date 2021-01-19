import java.util.Scanner;

public class Duke {
    public static void addLine() {
        System.out.println("    ------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Task[] inputArr = new Task[100];
        Scanner sc = new Scanner(System.in);
        addLine();
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        addLine();
        System.out.println();
        int n = 0;
        String dateTime, taskDesc = "";

        while(true) {
            String input = sc.next();
            switch(input) {
                case "bye":
                    break;
                case "list":
                    addLine();
                    for(int i = 1; i <= n; i++) {
                        Task task = inputArr[i - 1];
                        System.out.println("    " + i + ". " + "[" + task.getType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
                    }
                    addLine();
                    System.out.println();
                    break;
                case "done":
                    int taskNum = sc.nextInt();
                    Task task = inputArr[taskNum - 1];
                    task.toggleStatus();

                    addLine();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + "[" + task.getStatusIcon() + "] " + task.getDescription());
                    addLine();
                    System.out.println();
                    break;
                case "todo":
                    taskDesc = sc.nextLine();
                    Todo todo = new Todo(taskDesc);
                    inputArr[n] = todo;
                    n++;

                    addLine();
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + "[T]" + "[" + todo.getStatusIcon() + "] " + todo.getDescription());
                    System.out.println("    Now you have " + n + " tasks in the list.");
                    addLine();
                    System.out.println();
                    break;
                case "deadline":
                    taskDesc = sc.nextLine();
                    dateTime = taskDesc.split("/")[1];
                    taskDesc = taskDesc.split("/")[0] + "(by: " + dateTime.substring(3) + ")";
                    Deadline deadline = new Deadline(taskDesc);
                    inputArr[n] = deadline;
                    n++;

                    addLine();
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + "[D]" + "[" + deadline.getStatusIcon() + "] " + deadline.getDescription());
                    System.out.println("    Now you have " + n + " tasks in the list.");
                    addLine();
                    System.out.println();
                    break;
                case "event":
                    taskDesc = sc.nextLine();
                    dateTime = taskDesc.split("/")[1];
                    taskDesc = taskDesc.split("/")[0] + "(at: " + dateTime.substring(3) + ")";
                    Event event = new Event(taskDesc);
                    inputArr[n] = event;
                    n++;

                    addLine();
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + "[D]" + "[" + event.getStatusIcon() + "] " + event.getDescription());
                    System.out.println("    Now you have " + n + " tasks in the list.");
                    addLine();
                    System.out.println();
                    break;
                default:
                    inputArr[n] = new Task(taskDesc, "");
                    n++;
                    addLine();
                    System.out.println("    added: " + input);
                    addLine();
                    System.out.println();
                    break;
            }
            if(input.equals("bye")) break;

        }
        addLine();
        System.out.println("    Bye. Hope to see you again soon!");
        addLine();
        System.out.println();
    }
}