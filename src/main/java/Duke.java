import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println("    ____________________________________");
        System.out.println("    Hello! I'm Duke \n    What can I do for you?");
        System.out.println("    ____________________________________");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.nextLine();
          
            System.out.println("    ____________________________________");

            if (command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________");
                break;
            } else {
                System.out.println("    " + command);
                System.out.println("    ____________________________________");
            }
        }
        
        sc.close();
    }
}
