import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        String terminate_input = "bye";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What I can do for you?");
        System.out.println(line);
        System.out.println();

        while (true) {
            String input = sc.next();
            if (input.equals(terminate_input)) {
                System.out.println(line);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(line);
                System.out.println();
                break;
            } else {
                System.out.println(line);
                System.out.println("    " + input);
                System.out.println(line);
                System.out.println();
            }
        }
    }
}
