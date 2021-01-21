import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greet() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void addTask(String desc) {
        Task newTask = new Task(desc);
        taskList.add(newTask);
        System.out.println("added: " + desc);
    }

    public static void listTask() {
        for(int i = 0; i < taskList.size(); ++i) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                listTask();
            } else {
                addTask(input);
            }
        }
    }
}
