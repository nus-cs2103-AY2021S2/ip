import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final StringBuffer boundOfChatBox = new StringBuffer();
    private static final int lenOfChatBox = 50;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void setBoundOfChatBox() {
        boundOfChatBox.append('\n');
        boundOfChatBox.append("-".repeat(lenOfChatBox));
        boundOfChatBox.append('\n');
    }

    public static void formatInChatBox(String s) {
        System.out.println(boundOfChatBox + s + boundOfChatBox);
    }

    public static void init() {
        setBoundOfChatBox();
        String logo = "    __      __      ____ \n" +
                      "   /  \\    /  \\    / __ \\\n" +
                      "  / /\\ \\  / /\\ \\  | |  | |\n" +
                      " / /  \\ \\/ /  \\ \\ | |__| |\n" +
                      "/_/    \\__/    \\_\\ \\____/";
        String greeting = "Hello! I'm Momo\nWhat can I do for you?";
        formatInChatBox(logo);
        formatInChatBox(greeting + '\n');
    }

    public static void add(String description) {
        Task task = new Task(description);
        tasks.add(task);
        formatInChatBox("added: " + description + '\n');
    }

    public static void list() {
        int n = tasks.size();
        if (n == 0) {
            formatInChatBox("There is no task yet\n");
            return ;
        }
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < n; i++)
            buf.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        String res = new String(buf);
        formatInChatBox("Here are the tasks in your list:\n" + res);
    }

    public static void mark(int index) {
        Task taskToBeMarked = tasks.get(index - 1);
        taskToBeMarked.markedAsDone();
        formatInChatBox("Nice! I've marked this task as done:\n" + taskToBeMarked);
    }

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        formatInChatBox(goodbye);
    }

    public static void main(String[] args) {
        init();
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                return ;
            } else if (input.equals("list")) {
                list();
            } else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                int index = Integer.parseInt(input.substring(5));
                mark(index);
            } else {
                add(input);
            }
        } while(true);
    }
}
