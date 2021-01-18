import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final StringBuffer boundOfChatBox = new StringBuffer();
    private static final int lenOfChatBox = 50;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static enum command {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

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

    public static void addToDo(String des) throws TextException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a todo cannot be empty.\n");
            ToDo todo = new ToDo(des);
            add(todo);
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public static void addDeadline(String des) throws TextException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a todo cannot be empty.\n");
            if (des.contains("/by ")) {
                int endOfDescription = des.indexOf("/by ");
                String description = des.substring(0, endOfDescription);
                String deadline = des.substring(endOfDescription + 4);
                Deadline ddl = new Deadline(description, deadline);
                add(ddl);
            } else {
                throw new TextException("OOPS!!! Please enter '/by deadline' after description\n");
            }
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public static void addEvent(String des) throws TextException {
        try {
            if (des.isEmpty() || des.equals(" "))
                throw new TextException("OOPS!!! The description of a event cannot be empty.\n");
            if (des.contains("/at ")) {
                int endOfDescription = des.indexOf("/at ");
                String description = des.substring(0, endOfDescription);
                String time = des.substring(endOfDescription + 4);
                Event event = new Event(description, time);
                add(event);
            } else {
                throw new TextException("OOPS!!! Please enter '/by deadline' after description\n");
            }
        } catch (TextException e) {
            formatInChatBox(e.getMsgDes());
        }
    }

    public static void error() throws TextException {
        try {
            throw new TextException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
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
        formatInChatBox("Nice! I've marked this task as done:\n" + taskToBeMarked + "\n");
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

    public static void main(String[] args) throws TextException {
        init();
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.next();
            try {
                command c = command.valueOf(input.toUpperCase(Locale.ROOT));
                switch (c) {
                    case BYE:
                        exit();
                        return;
                    case LIST:
                        list();
                        break;
                    case DONE:
                        int i = sc.nextInt();
                        mark(i);
                        break;
                    case DELETE:
                        int j = sc.nextInt();
                        delete(j);
                        break;
                    case TODO:
                        String toDoDes = sc.nextLine();
                        addToDo(toDoDes);
                        break;
                    case DEADLINE:
                        String deadlineDes = sc.nextLine();
                        addDeadline(deadlineDes);
                        break;
                    case EVENT:
                        String eventDes = sc.nextLine();
                        addEvent(eventDes);
                        break;
                    default:
                        error();
                }
            } catch (IllegalArgumentException e) {
                error();
            }
        } while (true);
    }
}
