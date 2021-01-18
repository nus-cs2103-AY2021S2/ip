import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final StringBuffer boundOfChatBox = new StringBuffer();
    private static final int lenOfChatBox = 50;
    private static final ArrayList<String> tasks = new ArrayList<>();

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

    public static void add(String task) {
        tasks.add(task);
        formatInChatBox("added: " + task + '\n');
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
        formatInChatBox(res);
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
            switch (input) {
                case "bye":
                    exit();
                    return ;
                case "list":
                    list();
                    break;
                default:
                    add(input);
            }
        } while(true);
    }
}
