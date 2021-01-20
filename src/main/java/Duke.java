import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n" +
                            logo + "\nHello! I'm Duke\n" + "What can I do for you?\n" +
                            "____________________________________________________________\n");
        while(sc.hasNextLine()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                                    "Good bye! Stay calm and keep coding o7" +
                                    "\n____________________________________________________________\n");
                sc.close();
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                                    input +
                                    "\n____________________________________________________________\n");
            }
        }
    }
}
