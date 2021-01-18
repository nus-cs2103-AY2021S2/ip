import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();

    }

    private void start() {
        Scanner sc = new Scanner(System.in);

        echo(List.of("Hello! I'm Duke","What can I do for you?"));

        for (;;) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                return;
            }
            echo(input);
        }
    }

    private void echo(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t  " + s);
        System.out.println("\t____________________________________________________________");
    }

    private void echo(List<String> lst) {
        System.out.println("\t____________________________________________________________");
        for (String s : lst) {
            System.out.println("\t  " + s);
        }
        System.out.println("\t____________________________________________________________");
    }
}
