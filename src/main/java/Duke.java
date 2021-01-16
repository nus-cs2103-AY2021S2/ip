import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException{
        printGreeting();
//        performFirstTask();
        TaskList taskList = performSecondTask();
    }

    public static String printLine() {
        return "---------------------------";
    }
    public static void printGreeting() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(printLine());
        System.out.println(greet);
        System.out.println(printLine());
    }

    public static void performFirstTask() throws IOException {
        String cmd = new String();
        BufferedReader input = new
                BufferedReader(new InputStreamReader(System.in));
        while(true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if(cmd.equals("bye")){
                break;
            }
            System.out.println(cmd);
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
    }

    //Level-2 and Level-3 Tasks are implemented here.
    public static TaskList performSecondTask() throws IOException {
        String cmd = new String();
        TaskList taskList = new TaskList();
        BufferedReader input = new
                BufferedReader(new InputStreamReader(System.in));
        while(true) {
            cmd = input.readLine();
            System.out.println(printLine());
            if(cmd.equals("list")) {
                taskList.displayTasks();
            } else if(cmd.equals("bye")) {
                break;
            } else if(cmd.contains("done")) {
                int index = Integer.parseInt(cmd.substring(5));
                taskList.getTask(index - 1).setCompleted();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList.getTask(index-1));
            } else {
                taskList.addTask(new Task(cmd));
                System.out.println("added: " + cmd);
            }
            System.out.println(printLine());
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(printLine());
        input.close();
        return taskList;
    }
}
