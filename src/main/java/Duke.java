import java.util.*;

public class Duke {
    static String CHECKED = "[X] ";
    static String UNCHECKED = "[ ] ";
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ");
            if (cmd[0].equals("bye")) {
                exit = true;
            } else if (cmd[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println(tasks[i]);
                }
            } else if (cmd[0].equals("done")) {
                int taskNum = Integer.parseInt(cmd[1]) - 1;
                String str = tasks[taskNum].checkTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(str);
            } else {
                System.out.println("added: " + input);
                Task task = new Task(index + 1, input);
                tasks[index] = task;
                index++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

class Task {
    public int index;
    public String command;
    public boolean done;
    public static String CHECKED = "[X] ";
    public static String UNCHECKED = "[ ] ";

    public Task(int index, String command) {
        this.index = index;
        this.command = command;
        this.done = false;
    }

    public String checkTask() {
        this.done = true;
        String str = "  " + CHECKED + command;
        return str;
    }

    public String toString() {
        String str = String.valueOf(index) + ".";
        if (done) {
            str += CHECKED;
        } else {
            str += UNCHECKED;
        }
        str += command;
        return str;
    }
}