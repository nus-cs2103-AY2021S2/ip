import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        
        TaskList taskList = new TaskList();
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                taskList.show();
            } else if (task.split(" ")[0].equals("done")) {
                taskList.markDone(Integer.parseInt(task.split(" ")[1]));
            } else {
                taskList.add(task);
            }
            task = sc.nextLine();
        }
        exit();
        sc.close();
    }

    public static void printLineBreak() {
        System.out.println("\t____________________________________________________________");
    }

    public static void printIndented(String text) {
        System.out.println(String.format("\t%s", text));
    }

    public static void greeting() {
        printLineBreak();
        printIndented("Hello! I'm Duke");
        printIndented("What can I do for you?");
        printLineBreak();
    }

    public static void exit() {
        printLineBreak();
        printIndented("Bye. Hope to see you again soon!");
        printLineBreak();
    }
}
