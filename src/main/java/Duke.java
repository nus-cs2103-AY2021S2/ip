import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String introduction = "I'm Duke!\nWhat can I do for ya?\n";
        formatBox(introduction);

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye"))
                break;
            formatBox(input);
        }
        String bye = "Bye. Hope to see you again soon!";
        formatBox(bye);
    }

    /**
     * Duke speaks in chat boxes
     * @param str input string within chat boxes
     */
    public static void formatBox(String str) {
        System.out.println("------------------------------------\n");
        System.out.println(str);
        System.out.println("------------------------------------\n");
    }
}
