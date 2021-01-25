import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: " + hello);
        String cmd = sc.nextLine();
        String[] pre = cmd.split("\\s+");
        while(!cmd.equals("bye")) {
            int n;
            switch (pre[0]) {
                case "list":
                    printTasks(tasks);
                    break;
                case "done":
                    n = parseInt(pre[1]) - 1;
                    doneTask(tasks, n);
                    break;
                case "delete":
                    n = parseInt(pre[1]) - 1;
                    deleteTask(tasks, n);
                    break;
                case "deadline":
                    addDeadline(tasks, cmd);
                    break;
                case "event":
                    addEvent(tasks, cmd);
                    break;
                case "todo":
                    addToDo(tasks, pre);
                    break;
                default:
                    addError();
                    break;
            }

            cmd = sc.nextLine();
            pre = cmd.split("\\s+");
        }
        System.out.println("Duke: " + goodbye);
    }

    private static void deleteTask(ArrayList<Task> tasks, int n) {
        try {
            Task t = tasks.get(n);
            System.out.println("Noted. I've removed this task: ");
            tasks.remove(n);
            System.out.println(t);
            int size = tasks.size();
            printTotalTasks(size);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Wrong task ID");
        }
    }

    private static void printTotalTasks(int size) {
        if(size != 1) {
            System.out.printf("Now you have %d tasks in the list.\n", size);
        }
        else{
            System.out.printf("Now you have %d task in the list.\n", size);
        }
    }

    private static void doneTask(ArrayList<Task> tasks, int n) {
        try {
            tasks.set(n, tasks.get(n).finish());
            System.out.println("Nice! I've marked this task as done: \n");
            System.out.println(tasks.get(n));
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Wrong task ID");
        }
    }

    private static void addError() {
        System.out.println("Command not understood");
    }

    private static void addToDo(ArrayList<Task> tasks, String[] pre) {
        String s = "";
        for (int i = 1; i < pre.length; i++) {
            s = s + pre[i] + " ";
        }
        if (!s.equals("")) {
            tasks.add(new Todo(s,false));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        else {
            System.out.println("Please add a description for todo.");
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String cmd) {
        String[] pre2 = cmd.split("/at");
        try {
            String s = pre2[0];
            String t = pre2[1];
            System.out.println("Got it. I've added this task:");
            tasks.add(new Event(s, false, t));
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.out.println("Please enter a description for event");
        }
    }

    private static void addDeadline(ArrayList<Task> tasks, String cmd) {
        String[] pre2 = cmd.split("/by");
        try {
            String s = pre2[0];
            String t = pre2[1];
            System.out.println("Got it. I've added this task:");
            tasks.add(new Deadline(s, false, t));
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.out.println("Please enter a description for deadline");
        }
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }

    }
}
