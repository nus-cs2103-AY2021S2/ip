import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetUser();
        echo();
        byeUser();
    }
    private static void greetUser() {
        System.out.println("Hello, Im Duke. What do you want me to do???");
    }
    private static void byeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void echo() {
        while(sc.hasNext()) {
            String cmd = sc.next();
            if(cmd.equals("bye")) {
                byeUser();
                break;
            }
            System.out.println(cmd);
        }
    }
}
