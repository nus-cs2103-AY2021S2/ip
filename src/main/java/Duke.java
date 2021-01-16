import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        greeting();
        
        String userCmd = sc.nextLine();
        while (!userCmd.equals("bye")) {
            responseWrapper(userCmd);
            userCmd = sc.nextLine();
        }
        exit();
        sc.close();
    }

    public static void printLineBreak() {
        System.out.println("\t____________________________________________________________");
    }

    public static void responseWrapper(String msg) {
        printLineBreak();
        System.out.println(String.format("\t%s", msg));
        printLineBreak();
    }

    public static void greeting() {
        printLineBreak();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printLineBreak();
    }

    public static void exit() {
        responseWrapper("Bye. Hope to see you again soon!");
    }
}
