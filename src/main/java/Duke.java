import java.util.Scanner;

public class Duke {
    private static final StringBuffer boundOfChatBox = new StringBuffer();
    private static int lenOfChatBox = 50;

    public static void setBoundOfChatBox() {
        boundOfChatBox.append('\n');
        for (int i = 0; i < lenOfChatBox; i++) {
            boundOfChatBox.append('-');
        }
        boundOfChatBox.append('\n');
    }

    public static void formatInChatBox(String s) {
        StringBuffer res = new StringBuffer();
        res.append(boundOfChatBox).append(s).append(boundOfChatBox);
        System.out.println(res);
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
        formatInChatBox(greeting);
    }

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        formatInChatBox(goodbye);
    }

    public static void main(String[] args) {
        init();
        String input = "";
        Scanner sc = new Scanner(System.in);
        do {
            input = sc.nextLine();
            if(input.equals("bye")) {
                exit();
                return ;
            } else {
                formatInChatBox(input);
            }
        } while(true);
    }
}
