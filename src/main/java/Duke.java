import java.util.Scanner;

public class Duke {
    private static String indentation = "    ";
    private static String horizon = "-------------------------------------";
    private static void printReply(String reply){
        System.out.println(indentation+horizon);
        if (!reply.equals("bye")) {
            System.out.println(indentation + reply);
        }
        else {
            System.out.println(indentation + "Bye. (>_<) Hope to see you again soon! ");
        }
        System.out.println(indentation+horizon);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String hello = "Hello! I'm Duke Y(^_^)Y\n"+indentation+"What can I do for you?\n";
        String name = "";

        System.out.println("Hello from\n" + logo);
        printReply(hello);

        Scanner scanner = new Scanner(System.in);
        do {
            name = scanner.nextLine();
            printReply(name);
        }while(!name.equals("bye"));
    }
}
