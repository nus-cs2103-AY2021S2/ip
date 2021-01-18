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

    public static void add(Task task) {
        tasks.add(task);
        formatInChatBox("Got it. I've added this task:" + '\n'
                        + task + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.\n");
    }

    public static void analyzeTypeAndAdd(String input) throws TextException {
        try {
            if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                if (input.length() <= 5)
                    throw new TextException("OOPS!!! The description of a todo cannot be empty.\n");
                ToDo task = new ToDo(input.substring(5));
                add(task);
            } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                if (input.length() <= 9)
                    throw new TextException("OOPS!!! The description of a deadline cannot be empty.\n");
                if (input.contains("/by ")) {
                    int endOfDescription = input.indexOf("/by ");
                    String description = input.substring(9, endOfDescription);
                    String deadline = input.substring(endOfDescription + 4);
                    Deadline task = new Deadline(description, deadline);
                    add(task);
                } else {
                    throw new TextException("OOPS!!! Please enter '/by deadline' after description\n");
                }
            } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                if (input.length() <= 6)
                    throw new TextException("OOPS!!! The description of a event cannot be empty.\n");
                if (input.contains("/at ")) {
                    int endOfDescription = input.indexOf("/at ");
                    String description = input.substring(6, endOfDescription);
                    String time = input.substring(endOfDescription + 4);
                    Event task = new Event(description, time);
                    add(task);
                } else {
                    throw new TextException("OOPS!!! Please enter '/at time' after description\n");
                }
            } else {
                throw new TextException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
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

    public static void delete(int index) {
        Task taskToBeDeleted = tasks.remove(index - 1);
        formatInChatBox("Got it. I've removed this task:" + '\n'
                + taskToBeDeleted + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n");
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
            } else if (input.length() > 7 && input.substring(0, 7).equals("delete ")) {
                int index = Integer.parseInt(input.substring(7));
                delete(index);
            } else {
                analyzeTypeAndAdd(input);
            }
        } while(true);
    }
}
