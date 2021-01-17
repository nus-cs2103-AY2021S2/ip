import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        printGreeting();
//        performFirstTask();
        TaskList taskList = performSecondTask();
    }

    //Function is meant to print line for formatting.
    public static String printLine() {
        return "---------------------------";
    }

    //To input greeting.
    public static void printGreeting() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(printLine());
        System.out.println(greet);
        System.out.println(printLine());
    }

    //To perform Level-1 Task.
    public static void performFirstTask() throws IOException {
        String cmd = new String();
        BufferedReader input = new
            BufferedReader(new InputStreamReader(System.in));
        while (true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if (cmd.equals("bye")) {
                break;
            }
            System.out.println(cmd);
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
    }

    //Level-2 and Level-3 and Level-4 Tasks are implemented here.
    public static TaskList performSecondTask() throws IOException {
        String cmd = new String();
        TaskList taskList = new TaskList();
        BufferedReader input = new
            BufferedReader(new InputStreamReader(System.in));
        while (true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if (cmd.equals("list")) {
                taskList.displayTasks();
            } else if (cmd.equals("bye")) {
                break;
            } else if (cmd.contains("done")) {
                int index = Integer.parseInt(cmd.substring(5));
                taskList.getTask(index - 1).setCompleted();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList.getTask(index - 1));
            } else {
                taskList = performChildTask(taskList, cmd);
            }
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
        input.close();
        return taskList;
    }

    public static TaskList performChildTask(TaskList taskList, String cmd) {
        String task = new String();
        System.out.println("Got it. I've added this task: ");
        if (cmd.contains("todo")) {
            task = cmd.substring(5);
            ToDo newToDo = new ToDo(task);
            taskList.addTask(newToDo);
            System.out.println(newToDo);
        } else if (cmd.contains("deadline")) {
            int seg = cmd.indexOf("/");
            task = cmd.substring(9, seg);
            String by = cmd.substring(seg + 4);
            Deadline newDeadline = new Deadline(task, by);
            taskList.addTask(newDeadline);
            System.out.println(newDeadline);
        } else if (cmd.contains("event")) {
            int seg = cmd.indexOf("/");
            task = cmd.substring(6, seg);
            String at = cmd.substring(seg + 4);
            Event newEvent = new Event(task, at);
            taskList.addTask(newEvent);
            System.out.println(newEvent);
        } else {
            taskList.addTask(new Task(cmd));
            System.out.println("added: " + cmd);
        }
        System.out.println("Now you have "
            + taskList.getSize()
            + " tasks in the list.");
        return taskList;
    }
}
