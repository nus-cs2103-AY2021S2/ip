import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        String terminate_input = "bye";
        String list = "list";
        Scanner sc = new Scanner(System.in);
        System.out.println(line);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What I can do for you?");
        System.out.println(line);
        System.out.println();

        String[] todo = new String[100];
        int position = 0;

        while (true) {
            String input = sc.nextLine();
            System.out.println(line);
            if (input.equals(terminate_input)) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            } else if (input.equals(list)) {
                for (int i = 1; i < position + 1; i++) {
                    System.out.println(i + ". " + todo[i - 1]);
                }
            } else {
                System.out.println("    added: " + input);
                todo[position] = input;
                position += 1;
            }
            System.out.println(line);
            System.out.println();
        }
        System.out.println(line);
    }
}
