
public class UI {
    private static final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String greetingMessage = "Hello! I'm Duke\n" + "What can I do for you?";

    public static void greet() {
        System.out.println("Hello from");
        System.out.println();
        System.out.println(logo);
        System.out.println(greetingMessage);
    }
}
